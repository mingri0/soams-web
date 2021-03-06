package sckj.soams.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import sckj.soams.bean.LoginLogsBean;
import sckj.soams.entity.LoginLogs;

public interface LoginLogsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_logs
     *
     * @mbggenerated
     */
    @Delete({
        "delete from login_logs",
        "where loginseq = #{loginseq,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer loginseq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_logs
     *
     * @mbggenerated
     */
    @Insert({
        "insert into login_logs (loginseq, userid, ",
        "logindt, loginip, ",
        "agent)",
        "values (#{loginseq,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{logindt,jdbcType=TIMESTAMP}, #{loginip,jdbcType=VARCHAR}, ",
        "#{agent,jdbcType=VARCHAR})"
    })
    int insert(LoginLogs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_logs
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "loginseq, userid, logindt, loginip, agent",
        "from login_logs",
        "where loginseq = #{loginseq,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="loginseq", property="loginseq", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="logindt", property="logindt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="loginip", property="loginip", jdbcType=JdbcType.VARCHAR),
        @Result(column="agent", property="agent", jdbcType=JdbcType.VARCHAR)
    })
    LoginLogs selectByPrimaryKey(Integer loginseq);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_logs
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "loginseq, userid, logindt, loginip, agent",
        "from login_logs"
    })
    @Results({
        @Result(column="loginseq", property="loginseq", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="logindt", property="logindt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="loginip", property="loginip", jdbcType=JdbcType.VARCHAR),
        @Result(column="agent", property="agent", jdbcType=JdbcType.VARCHAR)
    })
    List<LoginLogs> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_logs
     *
     * @mbggenerated
     */
    @Update({
        "update login_logs",
        "set userid = #{userid,jdbcType=INTEGER},",
          "logindt = #{logindt,jdbcType=TIMESTAMP},",
          "loginip = #{loginip,jdbcType=VARCHAR},",
          "agent = #{agent,jdbcType=VARCHAR}",
        "where loginseq = #{loginseq,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LoginLogs record);
    
    @Insert({
        "insert into login_logs (userid, logindt, loginip, agent)",
        "values (#{userid,jdbcType=INTEGER}, #{logindt,jdbcType=TIMESTAMP}, #{loginip,jdbcType=VARCHAR}, #{agent,jdbcType=VARCHAR})"
    })
    int saveloginlogs(LoginLogs loginlogs);
    
    @Select({
        "select",
        "loginseq, userid, logindt, loginip, agent ",
        "from login_logs",
        "where userid = #{userid,jdbcType=INTEGER} order by logindt desc limit 1,1"
    })
    @Results({
    	@Result(column="loginseq", property="loginseq", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="logindt", property="logindt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="loginip", property="loginip", jdbcType=JdbcType.VARCHAR),
        @Result(column="agent", property="agent", jdbcType=JdbcType.VARCHAR)
    })
    LoginLogsBean getLoginlogsByUserid(Integer userid);
}