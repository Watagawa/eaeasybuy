package cn.easybuy.controller;


import cn.easybuy.SecurityUtils;
import cn.easybuy.pojo.News;
import cn.easybuy.pojo.Product;
import cn.easybuy.pojo.ProductCategory;
import cn.easybuy.pojo.User;
import cn.easybuy.service.INewsService;
import cn.easybuy.service.IProductCategoryService;
import cn.easybuy.service.IProductService;
import cn.easybuy.service.IUserService;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ProductCategoryVo;
import cn.easybuy.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 首页前端控制器
 * </p>
 *
 * @author 黄嘉豪 and 钟佳慧
 * @since 2020-06-22
 */
@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private IUserService userService;

    /**
     * 加载分类
     *
     * @author 黄嘉豪
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
     * 加载新闻
     *
     * @author 黄嘉豪
     */
    private void loadingNews(Model model) {
        //获取新闻
        List<News> news = newsService.queryNewsList(1, 5);
        model.addAttribute("news", news);
    }

    /**
     * 重定向首页
     *
     * @author 钟佳慧
     */
    @RequestMapping("/")
    public String home() {
        return "redirect:Home";
    }

    /**
     * 首页
     *
     * @author 黄嘉豪
     */
    @RequestMapping("Home")
    public String home(Model model) {

        loadingProductCategoryVoList(model);
        loadingNews(model);
        return "pre/index";
    }


    /**
     * 登录或注销用户
     *
     * @author 钟佳慧
     */
    @RequestMapping("Login")
    public String login(@RequestParam(value = "action", required = false) String action, HttpSession session) {
        //判断是否注销
        if ("loginOut".equals(action)) {
            // 删除用户
            session.removeAttribute("loginUser");
            // 删除购物车
            session.removeAttribute("cart");
        }
        //登录
        return "pre/login";
    }

    /**
     * 登录
     *
     * @author 钟佳慧
     */
    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("loginName") String loginName, @RequestParam("password") String password, HttpSession session) {
        //获取用户
        User user = userService.getUser(loginName);
        //判断密码是否一致
        if (user != null && Objects.equals(user.getPassword(), SecurityUtils.md5Hex(password))) {
            //设置用户
            session.setAttribute("loginUser", user);
            //设置购物车为空
            session.setAttribute("cart", new ShoppingCart());
            //登录成功 返回首页
            return "redirect:Home";
        }
        //登录失败 继续登录
        return "pre/login";
    }

    /**
     * 注册界面
     *
     * @author 钟佳慧
     */
    @RequestMapping("Register")
    public String register() {
        //注册
        return "/pre/register";
    }

    /**
     * 注册
     *
     * @author 钟佳慧
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Object doRegister(User user) {
        Map<String, Object> result = new HashMap<>();
        //设置为普通用户
        user.setType(0);
        //加密密码
        user.setPassword(SecurityUtils.md5Hex(user.getPassword()));
        //添加用户
        boolean isSc = userService.add(user);
        result.put("status", isSc ? 1 : 0);
        result.put("message", isSc ? "注册成功" : "注册失败");
        return result;
    }


    /**
     * 分类显示商品
     *
     * @author 黄嘉豪
     */
    @RequestMapping("Product")
    public String product(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "category", required = false) Integer category
            , @RequestParam(value = "level", required = false) Integer level
            , @RequestParam(value = "keyWord", required = false) String keyWord
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "rowPerPage", required = false) Integer rowPerPage
            , @RequestParam(value = "id", required = false) Integer id
    ) {

        currentPage = currentPage == null ? 1 : currentPage;

        switch (action) {
            //商品列表
            case "queryProductList":

                rowPerPage = rowPerPage == null ? 4 : rowPerPage;
                int productCount;
                String url;
                List<Product> productList;

                if (keyWord == null && level == null) {
                    //显示所有
                    productList = productService.getProductList(currentPage, rowPerPage);
                    productCount = productService.queryProductCount();
                    url = "Product?action=queryProductList";
                } else if (keyWord == null) {
                    //分类查询
                    productList = productService.getProductList(currentPage, rowPerPage, category, level);
                    productCount = productService.queryProductCount(category, level);
                    url = "Product?action=queryProductList&level=" + level + "&category=" + category;
                } else if (level == null) {
                    //搜索 模糊查询
                    productList = productService.getProductList(currentPage, rowPerPage, keyWord);
                    productCount = productService.queryProductCount(keyWord);
                    url = "Product?action=queryProductList&keyWord=" + keyWord;
                } else {
                    // 分类搜索
                    productList = productService.getProductList(currentPage, rowPerPage, keyWord, category, level);
                    productCount = productService.queryProductCount(keyWord, category, level);
                    url = "Product?action=queryProductList&level=" + level + "&category=" + category + "&keyWord=" + keyWord;
                }
                Pager pager = new Pager(productCount, rowPerPage, currentPage);
                pager.setUrl(url);

                model.addAttribute("productList", productList);
                model.addAttribute("pager", pager);
                model.addAttribute("total", productCount);
                model.addAttribute("keyWord", keyWord);
                //加载分类
                loadingProductCategoryVoList(model);
                return "/pre/product/queryProductList";
            //商品详情
            case "queryProductDeatil":
                //获取商品
                Product product = productService.getProductById(id);
                //加载分类
                loadingProductCategoryVoList(model);
                model.addAttribute("product", product);
                return "/pre/product/productDeatil";
            default:
                return "redirect:Home";
        }
    }
}
