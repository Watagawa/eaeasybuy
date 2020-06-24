package cn.easybuy.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {

    private Integer id;//ID
    private String serialNumber;//订单号
    private Integer userId;//用户id
    private String userAddress;//收货地址
    private Date createTime;//创建时间
    private Float cost;//订单总计价格

    private String loginName;//登录名称
    private List<cn.easybuy.utils.OrderDetail> orderDetailList;//订单明细列表


    public Order(cn.easybuy.pojo.Order order, List<cn.easybuy.utils.OrderDetail> orderDetailList) {
        this.id = order.getId();
        this.serialNumber = order.getSerialNumber();
        this.userId = order.getUserId();
        this.userAddress = order.getUserAddress();
        this.createTime = order.getCreateTime();
        this.cost = order.getCost();
        this.loginName = order.getLoginName();
        this.orderDetailList = orderDetailList;
    }
}
