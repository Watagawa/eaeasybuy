package cn.easybuy.service.impl;

import cn.easybuy.pojo.ProductCategory;
import cn.easybuy.mapper.ProductCategoryMapper;
import cn.easybuy.service.IProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类服务实现类
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {
    /**
     * 添加商品分类
     */
    @Override
    public boolean add(ProductCategory productCategory) {
        return baseMapper.insert(productCategory) == 1;
    }

    /**
     * 根据id删除商品
     */
    @Override
    public boolean deleteById(int parseLong) {
        return baseMapper.deleteById(parseLong) == 1;
    }

    /**
     * 修改商品分类
     */
    @Override
    public boolean update(ProductCategory productCategory) {
        return baseMapper.updateById(productCategory) == 1;
    }

    /**
     * 根据条件查询商品列表
     */
    @Override
    public List<ProductCategory> queryProductCategoryList(int from, int len) {
        return baseMapper.selectPage(new Page<>(from, len), null).getRecords();
    }

    /**
     * 根据id查询商品分类
     */
    @Override
    public ProductCategory queryProductCategoryById(int id) {
        return baseMapper.selectById(id);
    }

    /**
     * 根据商品分级别查询商品分类
     *
     * @param type 商品分类空白
     * @return 商品分类列表
     */
    @Override
    public List<ProductCategory> queryProductCategoryListByType(int type) {

        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return baseMapper.selectList(queryWrapper);

    }

    /**
     * 根据商品分级别和父类id查询商品分类
     *
     * @param parentId 父类id
     * @param type     商品分类空白
     * @return 商品分类列表
     */
    @Override
    public List<ProductCategory> queryProductCategoryListByParentId(int parentId, int type) {

        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId", parentId);
        queryWrapper.eq("type", type);
        return baseMapper.selectList(queryWrapper);
    }


    /**
     * 根据参数查询商品分类的数目
     */
    @Override
    public int queryProductCategoryCount() {
        return baseMapper.selectCount(null);
    }


}
