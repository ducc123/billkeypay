package payrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayVo {
    private Long payIdx;
    private String payCode;
    private Long payType;
    private String biliingKey;
    private String userCi;
    private String payPrice;
    private String payResult;
    private String createdAt;
    private Long apprPrice;
    private Long apprStatus;
    private String apprDatetime;
}
