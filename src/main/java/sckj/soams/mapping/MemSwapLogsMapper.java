package sckj.soams.mapping;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import sckj.soams.entity.MemSwapLogs;

public interface MemSwapLogsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_swap_logs
     *
     * @mbggenerated
     */
    @Delete({
        "delete from mem_swap_logs",
        "where hostid = #{hostid,jdbcType=VARCHAR}",
          "and recdt = #{recdt,jdbcType=TIMESTAMP}"
    })
    int deleteByPrimaryKey(@Param("hostid") String hostid, @Param("recdt") Date recdt);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_swap_logs
     *
     * @mbggenerated
     */
    @Insert({
        "insert into mem_swap_logs (hostid, recdt, ",
        "total, used, free, ",
        "ram)",
        "values (#{hostid,jdbcType=VARCHAR}, #{recdt,jdbcType=TIMESTAMP}, ",
        "#{total,jdbcType=VARCHAR}, #{used,jdbcType=VARCHAR}, #{free,jdbcType=VARCHAR}, ",
        "#{ram,jdbcType=VARCHAR})"
    })
    int insert(MemSwapLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_swap_logs
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, recdt, total, used, free, ram",
        "from mem_swap_logs",
        "where hostid = #{hostid,jdbcType=VARCHAR}",
          "and recdt = #{recdt,jdbcType=TIMESTAMP}"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="recdt", property="recdt", jdbcType=JdbcType.TIMESTAMP, id=true),
        @Result(column="total", property="total", jdbcType=JdbcType.VARCHAR),
        @Result(column="used", property="used", jdbcType=JdbcType.VARCHAR),
        @Result(column="free", property="free", jdbcType=JdbcType.VARCHAR),
        @Result(column="ram", property="ram", jdbcType=JdbcType.VARCHAR)
    })
    MemSwapLogs selectByPrimaryKey(@Param("hostid") String hostid, @Param("recdt") Date recdt);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_swap_logs
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, recdt, total, used, free, ram",
        "from mem_swap_logs"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="recdt", property="recdt", jdbcType=JdbcType.TIMESTAMP, id=true),
        @Result(column="total", property="total", jdbcType=JdbcType.VARCHAR),
        @Result(column="used", property="used", jdbcType=JdbcType.VARCHAR),
        @Result(column="free", property="free", jdbcType=JdbcType.VARCHAR),
        @Result(column="ram", property="ram", jdbcType=JdbcType.VARCHAR)
    })
    List<MemSwapLogs> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mem_swap_logs
     *
     * @mbggenerated
     */
    @Update({
        "update mem_swap_logs",
        "set total = #{total,jdbcType=VARCHAR},",
          "used = #{used,jdbcType=VARCHAR},",
          "free = #{free,jdbcType=VARCHAR},",
          "ram = #{ram,jdbcType=VARCHAR}",
        "where hostid = #{hostid,jdbcType=VARCHAR}",
          "and recdt = #{recdt,jdbcType=TIMESTAMP}"
    })
    int updateByPrimaryKey(MemSwapLogs record);
}