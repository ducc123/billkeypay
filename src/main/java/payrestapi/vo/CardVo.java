package payrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardVo {
    private String cardno;
    private String expired;
    private String crc;
    private String ref_id;
}
