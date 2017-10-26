package com.itheima.mapper;

import com.itheima.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	@Insert("insert int tb_user")
	void saveUser(User user);
    @Select("select * from tb_user where username =#{username}")
    User selectUser(String username);
}