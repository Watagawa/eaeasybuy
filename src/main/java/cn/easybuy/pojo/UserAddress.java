package cn.easybuy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 贺宗
 * @description 用户地址实体类
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_user_address")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户主键
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 是否是默认地址（1:是 0否）
     */
    @TableField("isDefault")
    private Integer isDefault;

    /**
     * 备注
     */
    private String remark;


}
