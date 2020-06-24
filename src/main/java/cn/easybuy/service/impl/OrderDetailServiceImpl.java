package cn.easybuy.service.impl;

import cn.easybuy.pojo.OrderDetail;
import cn.easybuy.mapper.OrderDetailMapper;
import cn.easybuy.service.IOrderDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单详情服务实现类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {


    @Override
    public List<OrderDetail> getOrderList(Integer orderId) {
        QueryWrapper<OrderDetail> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("orderId", orderId);

        return baseMapper.selectList(productQueryWrapper);
    }

    @Override
    public void insert(OrderDetail orderDetail) {
        baseMapper.insert(orderDetail);
    }
}
