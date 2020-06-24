package cn.easybuy.service.impl;

import cn.easybuy.pojo.Order;
import cn.easybuy.mapper.OrderMapper;
import cn.easybuy.pojo.OrderDetail;
import cn.easybuy.pojo.User;
import cn.easybuy.service.IOrderDetailService;
import cn.easybuy.service.IOrderService;
import cn.easybuy.utils.ShoppingCart;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单服务实现类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IOrderDetailService orderDetailService;

    @Override
    public List<Order> getOrderList(Integer userId, Integer currentPageNo, Integer pageSize) {
        IPage<Order> page = new Page<>(currentPageNo, pageSize);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        page = baseMapper.selectPage(page, orderQueryWrapper);
        return page.getRecords();
    }

    @Override
    public List<Order> getOrderList(Integer currentPageNo, Integer pageSize) {
        IPage<Order> page = new Page<>(currentPageNo, pageSize);
        page = baseMapper.selectPage(page, null);
        return page.getRecords();
    }

    @Override
    //支付订单
    public Order payShoppingCart(ShoppingCart cart, User user, String address) {

        Order order = new Order();
        order.setUserAddress(address);
        order.setLoginName(user.getLoginName());
        order.setUserId(user.getId());
        order.setCost(cart.getSum().floatValue());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String strDate = dtf.format(LocalDateTime.now());
        order.setCreateTime(new Date());
        order.setSerialNumber(strDate);
        baseMapper.insert(order);
        cart.items.forEach(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(item.getProduct().getId());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setCost(item.getCost());
            orderDetailService.insert(orderDetail);
        });
        return order;
    }

    @Override
    public int count(Integer userId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("userId", userId);
        return baseMapper.selectCount(orderQueryWrapper);
    }


}
