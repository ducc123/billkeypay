package payrestapi.service;

import payrestapi.exception.NoSuchDataException;
import payrestapi.vo.UserVo;
import payrestapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public UserVo getUserByUserCi(String userCi) {
        UserVo userVo = userMapper.getUserByUserCi(userCi);
        if (userVo == null)
            throw new NoSuchDataException("No such card exists. ref_id");
        return userVo;
    }
}
