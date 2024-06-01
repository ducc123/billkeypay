package payrestapi.dto.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import payrestapi.vo.PayVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPayListRespDto {
    private List<PayVo> payList;
    private int Resultcode = ResCode.SUCCESS.value();
    private String Resultmessage;
}
