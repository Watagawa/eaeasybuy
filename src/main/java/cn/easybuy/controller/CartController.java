package cn.easybuy.controller;

import cn.easybuy.pojo.Order;
import cn.easybuy.pojo.ProductCategory;
import cn.easybuy.pojo.User;
import cn.easybuy.pojo.UserAddress;
import cn.easybuy.service.*;
import cn.easybuy.utils.ProductCategoryVo;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 购物车前端控制器
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-23
 */
@Controller
@RequestMapping("Cart")
public class CartController {


    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductService productService;

    @Autowired
    private IUserAddressService userAddressService;

    @Autowired
    private IOrderService orderService;


    /**
     * 加载分类
     */
    private void loadingProductCategoryVoList(Model model) {

        List<ProductCategoryVo> productCategoryVoList1 = new ArrayList<>();

        //获取所有一级分类
        for (ProductCategory category1 : productCategoryService.queryProductCategoryListByType(1)) {

            ProductCategoryVo productCategoryVo1 = new ProductCategoryVo();
            //设置一级分类
            productCategoryVo1.setProductCategory(category1);

            List<ProductCategoryVo> productCategoryVoList2 = new ArrayList<>();
            //获取当前一级分类下的二级分类
            for (ProductCategory category2 : productCategoryService.queryProductCategoryListByParentId(category1.getId(), 2)) {

                ProductCategoryVo productCategoryVo2 = new ProductCategoryVo();
                //设置二级分类
                productCategoryVo2.setProductCategory(category2);

                List<ProductCategoryVo> productCategoryVoChildList2 = new ArrayList<>();
                //获取当前二级分类下的三级分类
                for (ProductCategory productCategory3 : productCategoryService.queryProductCategoryListByParentId(category2.getId(), 3)) {
                    ProductCategoryVo productCategoryVo3 = new ProductCategoryVo();
                    //设置三级分类
                    productCategoryVo3.setProductCategory(productCategory3);
                    productCategoryVoChildList2.add(productCategoryVo3);
                }

                productCategoryVo2.setProductCategoryVoList(productCategoryVoChildList2);

                productCategoryVoList2.add(productCategoryVo2);
            }
            //查询一级分类下的所有商品 并设置
            productCategoryVo1.setProductList(productService.getProductList(1, 6, category1.getId(), 1));
            productCategoryVo1.setProductCategoryVoList(productCategoryVoList2);
            productCategoryVoList1.add(productCategoryVo1);
        }

        model.addAttribute("productCategoryVoList", productCategoryVoList1);

    }

    /**
     * 添加购物车
     */
    @RequestMapping("add")
    @ResponseBody
    //加入购物车
    public ReturnResult product(@RequestParam("entityId") Integer entityId, @RequestParam("quantity") Integer quantity, HttpSession session) {
        ReturnResult result = new ReturnResult();
        //获取购物车
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            //判断是否存在购物车
            return result.returnFail("用户未登录");
        }
        return cart.addItem(productService.getProductById(entityId), quantity);
    }


    /**
     * 修改购物车商品数量
     */
    @RequestMapping("modCart")
    @ResponseBody
    public ReturnResult mod(
            @RequestParam("entityId") Integer entityId
            , @RequestParam("quantity") Integer quantity
            , HttpSession session
    ) {
        ReturnResult result = new ReturnResult();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            return result.returnFail("用户未登录");
        }

        if (quantity == 0) {
            cart.removeItem(entityId);
        } else {
            cart.modifyQuantity(entityId, quantity);
        }
        return result.returnSuccess();
    }


    /**
     * 刷新购物车
     */
    @RequestMapping("refreshCart")
    public String searchBar() {
        return "/common/pre/searchBar";
    }

    /**
     * 清空购物车
     */
    @RequestMapping("clearCart")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "/common/pre/searchBar";
    }


    /**
     * 结算购物车
     */
    @RequestMapping("toSettlement")
    public String toSettlement(Model model) {
        loadingProductCategoryVoList(model);
        return "/pre/settlement/toSettlement";
    }

    /**
     * 购物车
     */
    @RequestMapping("settlement1")
    public String settlement1() {
        return "/pre/settlement/settlement1";
    }

    /**
     * 地址
     */
    @RequestMapping("settlement2")
    public String settlement2(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        List<UserAddress> addressList = userAddressService.queryUserAddressList(user.getId());
        model.addAttribute("userAddressList", addressList);
        return "/pre/settlement/settlement2";
    }

    /**
     * 提交订单
     */
    @RequestMapping("settlement3")
    public String settlement3(Model model, @RequestParam("addressId") Integer addressId, @RequestParam("newAddress") String newAddress, @RequestParam("newRemark") String newRemark, HttpSession session) {

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loginUser");

        UserAddress address;

        if (newAddress != null && newRemark != null && newAddress.length() > 1 && newRemark.length() > 1) {
            address = new UserAddress();
            address.setUserId(user.getId());
            address.setAddress(newAddress);
            address.setRemark(newRemark);
            address.setIsDefault(1);
            userAddressService.add(address);
        } else
            address = userAddressService.getUserAddressById(addressId);
        Order order = orderService.payShoppingCart(cart, user, address.getAddress());
        cart.items.clear();
        model.addAttribute("currentOrder", order);
        return "/pre/settlement/settlement3";
    }
}
