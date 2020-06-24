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
 * @author 黄嘉豪
 * @description 商品实体类
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 价格
     */
    private Float price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 分类1
     */
    @TableField("categoryLevel1Id")
    private Integer categoryLevel1Id;

    /**
     * 分类2
     */
    @TableField("categoryLevel2Id")
    private Integer categoryLevel2Id;

    /**
     * 分类3
     */
    @TableField("categoryLevel3Id")
    private Integer categoryLevel3Id;

    /**
     * 文件名称
     */
    @TableField("fileName")
    private String fileName;

    /**
     * 是否删除(1：删除 0：未删除)
     */
    @TableField("isDelete")
    private Integer isDelete;


}
