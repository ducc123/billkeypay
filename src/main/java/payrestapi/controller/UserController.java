package payrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import payrestapi.consts.ResCode;
import payrestapi.dto.user.*;
import payrestapi.exception.NoSuchDataException;
import payrestapi.service.UserService;
import payrestapi.vo.UserVo;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{userCi}")
    public GetUserRespDto getUser(@PathVariable String userCi) {
        GetUserRespDto getUserRespDto = new GetUserRespDto();
        try {
            getUserRespDto.setUser(userService.getUserByUserCi(userCi));
        } catch (NoSuchDataException e) {
            getUserRespDto.setResultcode(ResCode.NO_SUCH_DATA.value());
            getUserRespDto.setResultmessage("No such user exists.");
        } catch (Exception e) {
            log.error("[UserController getUser]", e);
            getUserRespDto.setResultcode(ResCode.UNKNOWN.value());
            getUserRespDto.setResultmessage(e.getLocalizedMessage());
        }
        return getUserRespDto;
    }
}
