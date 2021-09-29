package com.example.android.mapper;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.ClassInfoExample;
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

public interface ClassInfoMapper {
    @SelectProvider(type=ClassInfoSqlProvider.class, method="countByExample")
    long countByExample(ClassInfoExample example);

    @DeleteProvider(type=ClassInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(ClassInfoExample example);

    @Delete({
        "delete from class_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into class_info (id, classes, ",
        "grade, speciality)",
        "values (#{id,jdbcType=BIGINT}, #{classes,jdbcType=VARCHAR}, ",
        "#{grade,jdbcType=VARCHAR}, #{speciality,jdbcType=VARCHAR})"
    })
    int insert(ClassInfo record);

    @InsertProvider(type=ClassInfoSqlProvider.class, method="insertSelective")
    int insertSelective(ClassInfo record);

    @SelectProvider(type=ClassInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="classes", property="classes", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR),
        @Result(column="speciality", property="speciality", jdbcType=JdbcType.VARCHAR)
    })
    List<ClassInfo> selectByExample(ClassInfoExample example);

    @Select({
        "select",
        "id, classes, grade, speciality",
        "from class_info",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT,id = true),
        @Result(column="classes", property="classes", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.VARCHAR),
        @Result(column="speciality", property="speciality", jdbcType=JdbcType.VARCHAR)
    })
    ClassInfo selectByPrimaryKey(Long id);

    @UpdateProvider(type=ClassInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    @UpdateProvider(type=ClassInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    @UpdateProvider(type=ClassInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ClassInfo record);

    @Update({
        "update class_info",
        "set classes = #{classes,jdbcType=VARCHAR},",
          "grade = #{grade,jdbcType=VARCHAR},",
          "speciality = #{speciality,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ClassInfo record);
}