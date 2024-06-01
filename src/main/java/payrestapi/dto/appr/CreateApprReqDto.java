package payrestapi.dto.appr;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import payrestapi.consts.ResCode;
import payrestapi.vo.PayVo;

import java.util.List;

@Data
public class CreateApprReqDto {
    private Long appr_price;
    private Long appr_status;
    private String pay_code;
    private String user_ci;

}
