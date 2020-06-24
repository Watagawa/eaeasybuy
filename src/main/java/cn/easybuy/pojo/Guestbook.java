package cn.easybuy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 贺宗
 * @description 留言博实体类
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("easybuy_guestbook")
public class Guestbook implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    private String remake;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;


}
