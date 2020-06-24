package cn.easybuy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 钟佳慧
 * @description 用户实体类
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    @TableField("loginName")
    private String loginName;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别(1:男 0：女)
     */
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField("identityCode")
    private String identityCode;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 类型（1：后台 0:前台）
     */
    private Integer type;

}
