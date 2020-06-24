package cn.easybuy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 贺宗
 * @description 订单实体类
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户主键
     */
    @TableField("userId")
    private Integer userId;

    @TableField("loginName")
    private String loginName;

    /**
     * 用户地址
     */
    @TableField("userAddress")
    private String userAddress;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 总消费
     */
    private Float cost;

    /**
     * 订单号
     */
    @TableField("serialNumber")
    private String serialNumber;


}
