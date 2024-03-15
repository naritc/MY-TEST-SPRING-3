package my.tiran.ctable.service;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.ctable.common.util.DateParserUtil;
import my.tiran.ctable.model.dto.Reserve;
import my.tiran.ctable.model.dto.ResponseReserve;
import my.tiran.ctable.model.entity.ReserveDetailEntity;
import my.tiran.ctable.model.entity.ReserveEntity;
import my.tiran.ctable.model.mapper.ReserveMapper;
import my.tiran.ctable.repository.ReserveDetailRepository;
import my.tiran.ctable.repository.ReserveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final ReserveDetailRepository reserveDetailRepository;

    private final ReserveMapper reserveMapper;


    @Transactional
    public ResponseReserve save(@Valid Reserve reserve) {

        ReserveEntity rev = reserveMapper.toReserveEntity(reserve);
        List<ReserveDetailEntity> details = new ArrayList<>();

        reserve.getReserveDetails().forEach(
                source -> {
                    ReserveDetailEntity target = reserveMapper.toReserveDetailEntity(source);

                    LocalTime reserveTime = LocalTime.parse(source.getReserveTime());
                    LocalTime exitTime = LocalTime.parse(source.getExitTime());
                    target.setReserve(rev);

                    target.setReserveDateTime(source.getReserveDate().atTime(reserveTime));
                    target.setExitDateTime(source.getReserveDate().atTime(exitTime));
                    target.setCreatedDatetime(DateParserUtil.now());
                    
                    target.setReserve(rev);
                    details.add(target);
                }
        );
        rev.setCreatedDatetime(DateParserUtil.now());

        this.reserveRepository.save(rev);
        this.reserveDetailRepository.saveAll(details);

        return ResponseReserve.builder()
                .message("Reserve Success")
                .prepareTableNumber(calculateTableInDay(rev.getCreatedDatetime()))
                .build();
    }
    
    public Integer calculateTableInDay(LocalDateTime calDate) {
        int result = 1;
        int tableUnit = 4;
        LocalDateTime queDate = calDate.toLocalDate().atStartOfDay();
        List<ReserveDetailEntity> details = this.reserveDetailRepository
                .findAllByReserveDateTime(queDate, queDate.plusDays(1));

        Set<ReserveDetailEntity> diff = new HashSet<>();

        for (int i = 0; i < details.size(); i++) {
            ReserveDetailEntity record1 = details.get(i);

            for (int j = i + 1; j < details.size(); j++) {
                ReserveDetailEntity record2 = details.get(j);

                if (isOverlap(record1, record2)) {
                    diff.add(record1);
                    diff.add(record2);
                }
            }
        }

        details.removeAll(diff);

        for (ReserveDetailEntity dtl : details) {
            if (Math.ceil((double) dtl.getNumberCustomer() / 4) > 1) {
                result += 1;
            }
        }

        for (ReserveDetailEntity dtl : diff) {
            double t = Math.ceil((double) dtl.getNumberCustomer() / 4);
            if (t > 1) {
                result += (int) t;
            } else {
                result += 1;
            }
        }

        return result;
    }

    private boolean isOverlap(ReserveDetailEntity record1, ReserveDetailEntity record2) {
        LocalDateTime reserveDateTime1 = record1.getReserveDateTime();
        LocalDateTime exitDateTime1 = record1.getExitDateTime();
        LocalDateTime reserveDateTime2 = record2.getReserveDateTime();
        LocalDateTime exitDateTime2 = record2.getExitDateTime();

        return reserveDateTime1.isBefore(exitDateTime2) && exitDateTime1.isAfter(reserveDateTime2);
    }
}
