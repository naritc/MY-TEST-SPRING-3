package my.tiran.ctable.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.tiran.ctable.common.util.ResponseUtil;
import my.tiran.ctable.model.dto.Reserve;
import my.tiran.ctable.model.dto.ResponseReserve;
import my.tiran.ctable.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ReserveApiController implements ReserveApi {
    private final ReserveService reserveService;
    @Override
    public ResponseEntity<ResponseReserve> reserve(Reserve reserve) throws Exception {
        ResponseReserve resp = reserveService.save(reserve);
        return ResponseUtil.success(resp);
    }
}
