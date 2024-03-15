package my.tiran.ctable.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import my.tiran.ctable.CtableApplication;
import my.tiran.ctable.config.H2TestProfileJPAConfig;
import my.tiran.ctable.model.dto.Reserve;
import my.tiran.ctable.model.dto.ReserveDetail;
import my.tiran.ctable.repository.ReserveDetailRepository;
import my.tiran.ctable.repository.ReserveRepository;
import my.tiran.ctable.service.ReserveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { H2TestProfileJPAConfig.class, CtableApplication.class})
public class ReserveTest {
    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    ReserveDetailRepository reserveDetailRepository;

    @Autowired
    ReserveService reserveService;


    @BeforeEach
    public void setUp() throws Exception {
        List<ReserveDetail> dtls = new ArrayList<ReserveDetail>();
        Reserve rev = new Reserve().builder().reserveDate(LocalDate.now()).build();
        for (int i = 0; i < 10; i++) {
            Mock date = new Mock();
            date.generateRandomDateTime();
            LocalDateTime rr = date.startDateTime;
            LocalDateTime ex = date.endDateTime;

            ReserveDetail dtl =  ReserveDetail.builder()
                    .reserveName("xxxxx")
                    .reserveDate(LocalDate.now())
                    .reserveTime(String.format("%02d:%02d", rr.getHour(), rr.getMinute()))
                    .exitTime(String.format("%02d:%02d", ex.getHour(), ex.getMinute()))
                    .mobileNo("000000")
                    .numberCustomer(new Random().nextInt(99))
                    .build();

            dtls.add(dtl);
        }
        rev.setReserveDetails(dtls);

        reserveService.save(rev);
    }

    @Test
    void test1() throws Exception {
       assert(!reserveDetailRepository.findAll().isEmpty());
    }
}
