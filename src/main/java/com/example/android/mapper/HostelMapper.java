package com.example.android.mapper;

import com.example.android.domain.Hostel;
import com.example.android.domain.HostelExample;
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

public interface HostelMapper {
    @SelectProvider(type=HostelSqlProvider.class, method="countByExample")
    long countByExample(HostelExample example);

    @DeleteProvider(type=HostelSqlProvider.class, method="deleteByExample")
    int deleteByExample(HostelExample example);

    @Delete({
        "delete from hostel",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into hostel (id, created_date, ",
        "last_modify, count, ",
        "grade, remain)",
        "values (#{id,jdbcType=BIGINT}, #{createdDate,jdbcType=TIMESTAMP}, ",
        "#{lastModify,jdbcType=TIMESTAMP}, #{count,jdbcType=INTEGER}, ",
        "#{grade,jdbcType=DOUBLE}, #{remain,jdbcType=INTEGER})"
    })
    int insert(Hostel record);

    @InsertProvider(type=HostelSqlProvider.class, method="insertSelective")
    int insertSelective(Hostel record);

    @SelectProvider(type=HostelSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="grade", property="grade", jdbcType=JdbcType.DOUBLE),
        @Result(column="remain", property="remain", jdbcType=JdbcType.INTEGER)
    })
    List<Hostel> selectByExample(HostelExample example);

    @Select({
        "select",
        "id, created_date, last_modify, count, grade, remain",
        "from hostel",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="created_date", property="createdDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modify", property="lastModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="grade", property="grade", jdbcType=JdbcType.DOUBLE),
        @Result(column="remain", property="remain", jdbcType=JdbcType.INTEGER)
    })
    Hostel selectByPrimaryKey(Long id);

    @UpdateProvider(type=HostelSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Hostel record, @Param("example") HostelExample example);

    @UpdateProvider(type=HostelSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Hostel record, @Param("example") HostelExample example);

    @UpdateProvider(type=HostelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Hostel record);

    @Update({
        "update hostel",
        "set created_date = #{createdDate,jdbcType=TIMESTAMP},",
          "last_modify = #{lastModify,jdbcType=TIMESTAMP},",
          "count = #{count,jdbcType=INTEGER},",
          "grade = #{grade,jdbcType=DOUBLE},",
          "remain = #{remain,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Hostel record);


}