package com.example.android.mapper;

import com.example.android.domain.User;
import com.example.android.domain.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;


public interface UserMapper {
    @SelectProvider(type=UserSqlProvider.class, method="countByExample")
    long countByExample(UserExample example);

    @DeleteProvider(type=UserSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExample example);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user (id, username, ",
        "password, status, ",
        "created_date, last_modify, ",
        "class_info, hostel, ",
        "student_number, admin)",
        "values (#{id,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{createdDate,jdbcType=TIMESTAMP}, #{lastModify,jdbcType=TIMESTAMP}, ",
        "#{classInfo,jdbcType=BIGINT}, #{hostel,jdbcType=BIGINT}, ",
        "#{studentNumber,jdbcType=VARCHAR}, #{admin,jdbcType=INTEGER})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="class_info", property="classInfo", jdbcType=JdbcType.BIGINT),
        @Result(column="hostel", property="hostel", jdbcType=JdbcType.BIGINT),
        @Result(column="student_number", property="studentNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin", property="admin", jdbcType=JdbcType.INTEGER)
    })
    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "id, username, password, status, created_date, last_modify, class_info, hostel, ",
        "student_number, admin",
        "from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="class_info", property="classInfo", jdbcType=JdbcType.BIGINT),
        @Result(column="hostel", property="hostel", jdbcType=JdbcType.BIGINT),
        @Result(column="student_number", property="studentNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin", property="admin", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "last_modify = #{lastModify,jdbcType=TIMESTAMP},",
          "class_info = #{classInfo,jdbcType=BIGINT},",
          "hostel = #{hostel,jdbcType=BIGINT},",
          "student_number = #{studentNumber,jdbcType=VARCHAR},",
          "admin = #{admin,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}