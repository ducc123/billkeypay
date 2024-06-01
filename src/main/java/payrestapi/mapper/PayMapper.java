package payrestapi.mapper;

import payrestapi.vo.PayCreateVo;
import payrestapi.vo.PayDetailVo;
import payrestapi.vo.PayVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {
    List<PayVo> getPayList();
    void insertPay(PayCreateVo payCreateVo);
    PayDetailVo getPayDetail(String pay_code);
}
