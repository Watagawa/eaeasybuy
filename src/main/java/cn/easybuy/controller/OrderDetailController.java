package cn.easybuy.controller;


import cn.easybuy.pojo.*;
import cn.easybuy.service.IOrderDetailService;
import cn.easybuy.service.IOrderService;
import cn.easybuy.service.IProductCategoryService;
import cn.easybuy.service.IProductService;
import cn.easybuy.utils.Pager;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单详情前端控制器
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */

@Controller
@RequestMapping("/admin")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;

    /**
     * 订单管理
     */
    @RequestMapping("order")
    public String order(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "userId", required = false) Integer userId
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "orderId", required = false) Integer orderId

    ) {
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 5 : pageSize;
        switch (action) {
            //我的订单
            case "index":


                int orderCount = orderService.count(userId);
                Pager pager = new Pager(orderCount, pageSize, currentPage);
                pager.setUrl("admin/order?action=index&userId=" + userId);
                //订单获取
                List<Order> order = orderService.getOrderList(userId, currentPage, pageSize);
                //转换前端所需格式
                List<cn.easybuy.utils.Order> orderList = loadingOrderList(order);
                model.addAttribute("orderList", orderList);
                model.addAttribute("pager", pager);
                model.addAttribute("menu", 1);
                return "/backend/order/orderList";

            case "queryOrderDeatil":
                // 查询订单明细
                List<cn.easybuy.utils.OrderDetail> orderDetailList = loadingOrderDetailList(orderDetailService.getOrderList(orderId));

                model.addAttribute("orderDetailList", orderDetailList);
                model.addAttribute("menu", 1);
                return "/backend/order/orderDetailList";

            case "queryAllOrder":
                //查询所有订单
                int total = orderService.count();
                Pager pager3 = new Pager(total, pageSize, currentPage);
                pager3.setUrl("admin/order?action=queryAllOrder");
                //所有订单获取
                List<Order> order3 = orderService.getOrderList(currentPage, pageSize);
                //转换前端所需格式
                List<cn.easybuy.utils.Order> orderList3 = loadingOrderList(order3);
                model.addAttribute("orderList", orderList3);
                model.addAttribute("pager", pager3);
                model.addAttribute("menu", 9);
                return "/backend/order/orderList";


        }

        return "";
    }


    private List<cn.easybuy.utils.Order> loadingOrderList(List<Order> order) {
        //获取订单
        return order.stream().map(this::loadingOrder).collect(Collectors.toList());
    }

    private cn.easybuy.utils.Order loadingOrder(Order order) {
        //获取订单详情
        List<OrderDetail> orderList = orderDetailService.getOrderList(order.getId());
        return new cn.easybuy.utils.Order(order, loadingOrderDetailList(orderList));
    }

    private List<cn.easybuy.utils.OrderDetail> loadingOrderDetailList(List<OrderDetail> orderDetailList) {
        return orderDetailList.stream().map(this::loadingOrderDetail).collect(Collectors.toList());
    }

    private cn.easybuy.utils.OrderDetail loadingOrderDetail(OrderDetail orderDetail) {
        //获取商品详情
        Product productById = productService.getProductById(orderDetail.getProductId());
        return new cn.easybuy.utils.OrderDetail(orderDetail, productById);
    }
}

