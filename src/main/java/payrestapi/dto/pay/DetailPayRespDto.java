package payrestapi.dto.pay;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import payrestapi.consts.ResCode;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailPayRespDto {
    private int pay_idx;
    private int resultcode = ResCode.SUCCESS.value();
    private String resultmessage;
}
