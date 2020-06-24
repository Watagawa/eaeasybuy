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
 * @author 贺宗
 * @description 订单详情实体类
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_order_detail")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单主键
     */
    @TableField("orderId")
    private Integer orderId;

    /**
     * 商品主键
     */
    @TableField("productId")
    private Integer productId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 消费
     */
    private Float cost;


}
