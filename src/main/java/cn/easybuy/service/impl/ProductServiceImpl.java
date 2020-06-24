package cn.easybuy.service.impl;

import cn.easybuy.pojo.Product;
import cn.easybuy.mapper.ProductMapper;
import cn.easybuy.service.IProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品服务实现类
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public boolean add(Product product) {
        return baseMapper.insert(product) == 1;
    }

    @Override
    public boolean deleteProductById(int id) {
        return baseMapper.deleteById(id) == 1;
    }

    @Override
    public boolean update(Product product) {
        return baseMapper.updateById(product) == 1;
    }

    @Override
    public Product getProductById(int id) {
        return baseMapper.selectById(id);
    }


    @Override
    public List<Product> getProductList(int from, int len, String proName, int categoryId, int level) {
        //商品名字 模糊查询    类别 id     级别
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.like("name", proName)
                .eq(String.format("categoryLevel%dId", level), categoryId);

        return baseMapper.selectPage(new Page<>(from, len), productQueryWrapper).getRecords();

    }

    @Override
    public List<Product> getProductList(int from, int len, int categoryId, int level) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq(String.format("categoryLevel%dId", level), categoryId);
        return baseMapper.selectPage(new Page<>(from, len), productQueryWrapper).getRecords();
    }

    @Override
    public List<Product> getProductList(int from, int len, String proName) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.like("name", proName);

        return baseMapper.selectPage(new Page<>(from, len), productQueryWrapper).getRecords();
    }

    @Override
    public List<Product> getProductList(int from, int len) {
        return baseMapper.selectPage(new Page<>(from, len), null).getRecords();
    }

    @Override
    public int queryProductCount(String proName, int categoryId, int level) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.like("name", proName);
        if (level == 1)
            productQueryWrapper.eq("categoryLevel1Id", categoryId);
        else if (level == 2)
            productQueryWrapper.eq("categoryLevel2Id", categoryId);
        else if (level == 3)
            productQueryWrapper.eq("categoryLevel3Id", categoryId);
        return baseMapper.selectCount(productQueryWrapper);
    }

    @Override
    public int queryProductCount(String proName) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.like("name", proName);
        return baseMapper.selectCount(productQueryWrapper);
    }

    @Override
    public int queryProductCount(int categoryId, int level) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        if (level == 1)
            productQueryWrapper.eq("categoryLevel1Id", categoryId);
        else if (level == 2)
            productQueryWrapper.eq("categoryLevel2Id", categoryId);
        else if (level == 3)
            productQueryWrapper.eq("categoryLevel3Id", categoryId);
        return baseMapper.selectCount(productQueryWrapper);
    }

    @Override
    public int queryProductCount() {
        return baseMapper.selectCount(null);
    }
}
