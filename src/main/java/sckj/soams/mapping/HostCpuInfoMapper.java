package sckj.soams.mapping;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import sckj.soams.entity.HostCpuInfo;

public interface HostCpuInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_cpu_info
     *
     * @mbggenerated
     */
    @Delete({
        "delete from host_cpu_info",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String hostid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_cpu_info
     *
     * @mbggenerated
     */
    @Insert({
        "insert into host_cpu_info (hostid, cache_size, ",
        "cpu_cores, vendor, ",
        "mhz, model)",
        "values (#{hostid,jdbcType=VARCHAR}, #{cache_size,jdbcType=VARCHAR}, ",
        "#{cpu_cores,jdbcType=INTEGER}, #{vendor,jdbcType=VARCHAR}, ",
        "#{mhz,jdbcType=VARCHAR}, #{model,jdbcType=VARCHAR})"
    })
    int insert(HostCpuInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_cpu_info
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, cache_size, cpu_cores, vendor, mhz, model",
        "from host_cpu_info",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="cache_size", property="cache_size", jdbcType=JdbcType.VARCHAR),
        @Result(column="cpu_cores", property="cpu_cores", jdbcType=JdbcType.INTEGER),
        @Result(column="vendor", property="vendor", jdbcType=JdbcType.VARCHAR),
        @Result(column="mhz", property="mhz", jdbcType=JdbcType.VARCHAR),
        @Result(column="model", property="model", jdbcType=JdbcType.VARCHAR)
    })
    HostCpuInfo selectByPrimaryKey(String hostid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_cpu_info
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, cache_size, cpu_cores, vendor, mhz, model",
        "from host_cpu_info"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="cache_size", property="cache_size", jdbcType=JdbcType.VARCHAR),
        @Result(column="cpu_cores", property="cpu_cores", jdbcType=JdbcType.INTEGER),
        @Result(column="vendor", property="vendor", jdbcType=JdbcType.VARCHAR),
        @Result(column="mhz", property="mhz", jdbcType=JdbcType.VARCHAR),
        @Result(column="model", property="model", jdbcType=JdbcType.VARCHAR)
    })
    List<HostCpuInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_cpu_info
     *
     * @mbggenerated
     */
    @Update({
        "update host_cpu_info",
        "set cache_size = #{cache_size,jdbcType=VARCHAR},",
          "cpu_cores = #{cpu_cores,jdbcType=INTEGER},",
          "vendor = #{vendor,jdbcType=VARCHAR},",
          "mhz = #{mhz,jdbcType=VARCHAR},",
          "model = #{model,jdbcType=VARCHAR}",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(HostCpuInfo record);
}