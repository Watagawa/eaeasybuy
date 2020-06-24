package cn.easybuy.mapper;

import cn.easybuy.pojo.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

}
