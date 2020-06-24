package cn.easybuy.utils;

import cn.easybuy.pojo.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetail implements Serializable {
    private Integer id;
    private Integer orderId;
    private Integer quantity;
    private Float cost;
    private Integer productId;
    private Product product;

    public OrderDetail(cn.easybuy.pojo.OrderDetail orderDetail, Product product) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrderId();
        this.quantity = orderDetail.getQuantity();
        this.cost = orderDetail.getCost();
        this.productId = orderDetail.getProductId();
        this.product = product;

    }


}
