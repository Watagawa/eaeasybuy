package cn.easybuy.service;

import cn.easybuy.pojo.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单详情服务类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */
public interface IOrderDetailService extends IService<OrderDetail> {


    /**
     * 根据id获取订单详情列表
     */
    public List<OrderDetail> getOrderList(Integer orderId);

    /**
     * 插入订单详情
     */
    public void insert(OrderDetail orderDetail);

}
