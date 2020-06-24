package cn.easybuy.service;

import cn.easybuy.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品服务类
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
public interface IProductService extends IService<Product> {

    /**
     * 添加商品
     *
     * @param product 商品
     * @return 是否成功
     */
    public boolean add(Product product);

    /**
     * 根据id删除商品
     *
     * @param id 商品id
     * @return 是否成功
     */
    public boolean deleteProductById(int id);

    /**
     * 更新商品
     *
     * @param product 更新商品
     * @return 是否成功
     */
    public boolean update(Product product);

    /**
     * 根据id获取商品
     *
     * @param id 商品id
     * @return 商品
     */
    public Product getProductById(int id);

    /**
     * 查询商品
     *
     * @param from       开始页
     * @param len        页大小
     * @param proName    模糊查询名
     * @param categoryId 分类id
     * @param level      几级分类
     * @return 商品列表
     */
    public List<Product> getProductList(int from, int len, String proName, int categoryId, int level);

    /**
     * 查询商品
     *
     * @param from       开始页
     * @param len        页大小
     * @param categoryId 分类id
     * @param level      几级分类
     * @return 商品列表
     */
    public List<Product> getProductList(int from, int len, int categoryId, int level);

    /**
     * 查询商品
     *
     * @param from    开始页
     * @param len     页大小
     * @param proName 模糊查询名
     * @return 商品列表
     */
    public List<Product> getProductList(int from, int len, String proName);

    /**
     * 查询商品
     *
     * @param from 开始页
     * @param len  页大小
     * @return 商品列表
     */
    public List<Product> getProductList(int from, int len);

    /**
     * 查询商品数量
     *
     * @param proName    模糊查询名
     * @param categoryId 分类id
     * @param level      几级分类
     * @return 商品数量
     */
    public int queryProductCount(String proName, int categoryId, int level);

    /**
     * 查询商品数量
     *
     * @param proName 模糊查询名
     * @return 商品数量
     */
    public int queryProductCount(String proName);

    /**
     * 查询商品数量
     *
     * @param categoryId 分类id
     * @param level      几级分类
     * @return 商品数量
     */
    public int queryProductCount(int categoryId, int level);

    /**
     * 查询商品数量
     *
     * @return 商品数量
     */
    public int queryProductCount();
}
