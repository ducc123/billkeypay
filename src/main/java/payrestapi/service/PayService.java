package payrestapi.service;

import payrestapi.exception.NoSuchDataException;
import payrestapi.mapper.PayMapper;
import payrestapi.vo.PayCreateVo;
import payrestapi.vo.PayDetailVo;
import payrestapi.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService {
    @Autowired
    private PayMapper payMapper;

    public List<PayVo> getPayList() {
        List<PayVo> list = payMapper.getPayList();
        if (list.isEmpty())
            throw new NoSuchDataException("No pay exists.");
        return list;
    }

    public int getPayDetail(String pay_code) {
        PayDetailVo payDetailVo = payMapper.getPayDetail(pay_code);
        if (payDetailVo.getPayIdx()==null)
            throw new NoSuchDataException("존재하는 pay_code가 아닙니다.");
        return Math.toIntExact(payDetailVo.getPayIdx());
    }

    public void createPay(PayCreateVo payCreateVo) {
        payMapper.insertPay(payCreateVo);
    }
}
