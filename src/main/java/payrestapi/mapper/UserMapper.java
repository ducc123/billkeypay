package payrestapi.mapper;

import payrestapi.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserVo getUserByUserCi(String userCi);
}
