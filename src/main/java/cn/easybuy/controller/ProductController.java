package cn.easybuy.controller;


import cn.easybuy.pojo.Product;
import cn.easybuy.service.IProductCategoryService;
import cn.easybuy.service.IProductService;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * <p>
 * 商品前端控制器
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductService productService;

    /**
     * 商品管理
     */
    @RequestMapping("product")
    public String product(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "id", required = false) Integer id
    ) {


        switch (action) {
            //商品列表分页查询
            case "index":
                int rowPerPage = pageSize == null ? 5 : pageSize;
                currentPage = currentPage == null ? 1 : currentPage;
                int productCount = productService.queryProductCount();
                Pager pager = new Pager(productCount, rowPerPage, currentPage);
                pager.setUrl("admin/product?action=index");

                model.addAttribute("menu", 5);
                model.addAttribute("productList", productService.getProductList(currentPage, rowPerPage));
                model.addAttribute("pager", pager);
                return "/backend/product/productList";
            //修改商品信息
            case "toUpdateProduct":
                model.addAttribute("menu", 6);
                model.addAttribute("productCategoryList1", productCategoryService.queryProductCategoryListByType(1));
                model.addAttribute("productCategoryList2", productCategoryService.queryProductCategoryListByType(2));
                model.addAttribute("productCategoryList3", productCategoryService.queryProductCategoryListByType(3));
                model.addAttribute("product", productService.getProductById(id));
                return "/backend/product/toAddProduct";
            //添加商品
            case "toAddProduct":
                model.addAttribute("menu", 6);
                model.addAttribute("productCategoryList1", productCategoryService.queryProductCategoryListByType(1));
                model.addAttribute("productCategoryList2", productCategoryService.queryProductCategoryListByType(2));
                model.addAttribute("productCategoryList3", productCategoryService.queryProductCategoryListByType(3));
                model.addAttribute("product", new Product());
                return "/backend/product/toAddProduct";


        }

        return "";

    }

    /**
     * 删除商品
     */
    @RequestMapping("product/deleteById")
    @ResponseBody

    public ReturnResult addProduct(@RequestParam(value = "id", required = false) int id) {
        ReturnResult result = new ReturnResult();
        if (productService.deleteProductById(id)) {
            return result.returnSuccess();
        }
        return result.returnFail("失败");
    }


    /**
     * 商品修改或添加
     */
    @PostMapping("product/addProduct")
    public String addProduct(Product product, MultipartFile photo, HttpServletRequest request) {

        try {
            //图片保存路径
            String url = request.getSession().getServletContext().getRealPath("/files");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String strDate = dtf.format(LocalDateTime.now());
            String originalFilename = strDate + photo.getOriginalFilename();
            photo.transferTo(new File(url + "\\" + originalFilename));
            product.setFileName(originalFilename);
        } catch (IOException e) {
            System.out.println("文件保存失败");
        }
        if (product.getId() != null) {
            //修改
            productService.update(product);
        } else {
            //添加
            productService.add(product);
        }
        return "redirect:../product?action=index";
    }


}

