package payrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApprVo {
    private Long apprPrice;
    private Long apprStatus;
    private String payCode;
    private String userCi;

}
