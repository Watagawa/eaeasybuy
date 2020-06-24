package cn.easybuy.service;

import cn.easybuy.pojo.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类服务类
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
public interface IProductCategoryService extends IService<ProductCategory> {
    /**
     * 添加商品分类
     */
    public boolean add(ProductCategory productCategory);

    /**
     * 根据id删除分类
     */
    public boolean deleteById(int parseLong);

    /**
     * 修改商品分类
     */
    public boolean update(ProductCategory productCategory);

    /**
     * 根据条件查询商品分类列表
     */
    public List<ProductCategory> queryProductCategoryList(int from, int len);

    /**
     * 根据id查询商品分类
     */
    public ProductCategory queryProductCategoryById(int id);

    /**
     * 根据属性查询商品分类
     */
    public List<ProductCategory> queryProductCategoryListByType(int type);

    /**
     * 根据parentId查询商品分类
     */
    public List<ProductCategory> queryProductCategoryListByParentId(int parentId, int type);

    /**
     * 根据参数查询商品分类的数目
     */
    public int queryProductCategoryCount();


}
