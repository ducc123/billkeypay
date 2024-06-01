package payrestapi.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import payrestapi.consts.ResCode;
import payrestapi.vo.UserVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserRespDto {
    private UserVo user;
    private int resultcode = ResCode.SUCCESS.value();
    private String resultmessage;
}
