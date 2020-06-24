package cn.easybuy.service;

import cn.easybuy.pojo.Order;
import cn.easybuy.pojo.User;
import cn.easybuy.utils.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单服务类
 * </p>
 *
 * @author 贺宗 and 黄嘉豪
 * @since 2020-06-21
 */
public interface IOrderService extends IService<Order> {
    /**
     * 根据订单id查询订单明细列表
     */
    public List<Order> getOrderList(Integer userId,
                                    Integer currentPageNo,
                                    Integer pageSize);

    /**
     * 分页显示订单信息列表
     */
    public List<Order> getOrderList(Integer currentPageNo, Integer pageSize);

    /**
     * 结算订单
     *
     * @param cart    购物车
     * @param user    用户
     * @param address 地址
     * @return 订单
     * @author 黄嘉豪
     */
    public Order payShoppingCart(ShoppingCart cart, User user, String address);

    /**
     * 计算订单数量
     */
    public int count(Integer userId);

}
