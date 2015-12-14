package sckj.soams.mapping;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import sckj.soams.entity.Hosts;

public interface HostsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hosts
     *
     * @mbggenerated
     */
    @Delete({
        "delete from hosts",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String hostid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hosts
     *
     * @mbggenerated
     */
    @Insert({
        "insert into hosts (hostid, hostname, ",
        "othername, ip, status, ",
        "heartbeat, memtotal, ",
        "labels)",
        "values (#{hostid,jdbcType=VARCHAR}, #{hostname,jdbcType=VARCHAR}, ",
        "#{othername,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{heartbeat,jdbcType=TIMESTAMP}, #{memtotal,jdbcType=VARCHAR}, ",
        "#{labels,jdbcType=VARCHAR})"
    })
    int insert(Hosts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hosts
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, hostname, othername, ip, status, heartbeat, memtotal, labels",
        "from hosts",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="hostname", property="hostname", jdbcType=JdbcType.VARCHAR),
        @Result(column="othername", property="othername", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="heartbeat", property="heartbeat", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="memtotal", property="memtotal", jdbcType=JdbcType.VARCHAR),
        @Result(column="labels", property="labels", jdbcType=JdbcType.VARCHAR)
    })
    Hosts selectByPrimaryKey(String hostid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hosts
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "hostid, hostname, othername, ip, status, heartbeat, memtotal, labels",
        "from hosts"
    })
    @Results({
        @Result(column="hostid", property="hostid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="hostname", property="hostname", jdbcType=JdbcType.VARCHAR),
        @Result(column="othername", property="othername", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="heartbeat", property="heartbeat", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="memtotal", property="memtotal", jdbcType=JdbcType.VARCHAR),
        @Result(column="labels", property="labels", jdbcType=JdbcType.VARCHAR)
    })
    List<Hosts> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hosts
     *
     * @mbggenerated
     */
    @Update({
        "update hosts",
        "set hostname = #{hostname,jdbcType=VARCHAR},",
          "othername = #{othername,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "heartbeat = #{heartbeat,jdbcType=TIMESTAMP},",
          "memtotal = #{memtotal,jdbcType=VARCHAR},",
          "labels = #{labels,jdbcType=VARCHAR}",
        "where hostid = #{hostid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Hosts record);
}