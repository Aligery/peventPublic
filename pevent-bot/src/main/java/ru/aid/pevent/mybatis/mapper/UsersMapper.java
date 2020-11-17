package ru.aid.pevent.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import ru.aid.pevent.model.entity.user.PeventUser;

import java.util.List;

public interface UsersMapper {

    List<PeventUser> selectAll();

    void insertNewUser(@Param("obj") PeventUser peventUser);

}
