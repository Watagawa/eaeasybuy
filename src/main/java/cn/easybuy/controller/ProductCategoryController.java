package cn.easybuy.controller;


import cn.easybuy.pojo.ProductCategory;
import cn.easybuy.utils.ProductCategorys;
import cn.easybuy.service.IProductCategoryService;
import cn.easybuy.service.IProductService;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商品分类前端控制器
 * </p>
 *
 * @author 黄嘉豪
 * @since 2020-06-21
 */
@Controller
@RequestMapping("/admin")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductService productService;

    /**
     * 添加商品分类
     */
    @RequestMapping("addProductCategory")
    @ResponseBody
    public ReturnResult addProductCategory(
            @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "type", required = false) Integer type
            , @RequestParam(value = "productCategoryLevel1", required = false) Integer productCategoryLevel1
            , @RequestParam(value = "productCategoryLevel2", required = false) Integer productCategoryLevel2
    ) {
        ReturnResult result = new ReturnResult();
        Integer parentId = type.equals(2) ? productCategoryLevel1 : type.equals(3) ? productCategoryLevel2 : 0;

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setParentId(parentId);
        productCategory.setType(type);
        productCategory.setIconClass("");
        if (productCategoryService.add(productCategory))
            return result.returnSuccess();
        return result.returnFail("失败");
    }

    /**
     * 删除商品分类
     */
    @RequestMapping("deleteProductCategory")
    @ResponseBody
    public ReturnResult deleteProductCategory(
            @RequestParam(value = "id", required = false) int id
            , @RequestParam(value = "type", required = false) Integer type
    ) {
        ReturnResult result = new ReturnResult();
        //判断是否有子类
        if ((!type.equals(3)) && productCategoryService.count(new QueryWrapper<ProductCategory>().eq("parentId", id)) > 0) {
            return result.returnFail("已经存在子分类，不能删除");
        }
        //查询是否有商品
        if (productService.queryProductCount(id, type) > 0) {
            return result.returnFail("已经存在商品，不能删除");
        }
        if (productCategoryService.deleteById(id)) {
            return result.returnSuccess();
        }
        return result.returnFail("失败");

    }

    /**
     * 修改商品分类
     */
    @RequestMapping("modifyProductCategory")
    @ResponseBody
    public ReturnResult modifyProductCategory(@RequestParam(value = "id", required = false) Integer id
            , @RequestParam(value = "productCategoryLevel1", required = false) Integer productCategoryLevel1
            , @RequestParam(value = "productCategoryLevel2", required = false) Integer productCategoryLevel2
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "type", required = false) Integer type
    ) {
        ReturnResult result = new ReturnResult();
        Integer parentId = type.equals(2) ? productCategoryLevel1 : type.equals(3) ? productCategoryLevel2 : 0;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setParentId(parentId);
        productCategory.setName(name);
        productCategory.setType(type);

        if (productCategoryService.update(productCategory))
            return result.returnSuccess();
        return result.returnFail("失败");
    }

    /**
     * 商品分类管理
     */
    @RequestMapping("productCategory")
    public String productCategory(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "id", required = false) Integer id

            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "type", required = false) Integer type
    ) {


        switch (action) {
            case "index":
                //分类列表
                pageSize = pageSize == null ? 8 : pageSize;
                currentPage = currentPage == null ? 1 : currentPage;
                int total = productCategoryService.queryProductCategoryCount();

                Pager pager = new Pager(total, pageSize, currentPage);
                pager.setUrl("admin/productCategory?action=index");

                //获取分类列表
                List<ProductCategory> productCategories = productCategoryService.queryProductCategoryList(currentPage, pageSize);

                List<ProductCategorys> collect = productCategories.stream().map(x -> {
                    String parentName = "无";
                    if (!Objects.equals(x.getParentId(), 0)) {
                        Optional<ProductCategory> first = productCategories.stream().filter(t -> Objects.equals(t.getId(), x.getParentId())).findFirst();
                        if (first.isPresent())
                            //判断是否有父类
                            parentName = first.get().getName();
                    }
                    return new ProductCategorys(x, parentName);
                }).collect(Collectors.toList());


                model.addAttribute("productCategoryList", collect);
                model.addAttribute("pager", pager);
                model.addAttribute("menu", 4);
                return "/backend/productCategory/productCategoryList";

            case "toAddProductCategory":
                //添加商品分类
                model.addAttribute("productCategoryList1", productCategoryService.queryProductCategoryListByType(1));
                model.addAttribute("productCategoryList2", productCategoryService.queryProductCategoryListByType(2));
                model.addAttribute("productCategory", new ProductCategory());
                return "/backend/productCategory/toAddProductCategory";
            case "toUpdateProductCategory":
                //修改商品分类
                ProductCategory category = productCategoryService.getById(id);
                model.addAttribute("productCategory", category);

                //判断分类级别
                if (category.getType() > 1) {
                    model.addAttribute("productCategoryList1", productCategoryService.queryProductCategoryListByType(1));
                }
                if (category.getType() > 2) {
                    model.addAttribute("parentProductCategory", productCategoryService.getById(category.getParentId()));
                    model.addAttribute("productCategoryList2", productCategoryService.queryProductCategoryListByType(2));
                }
                return "/backend/productCategory/toAddProductCategory";
            default:
                return "redirect:productCategory?action=index";
        }

    }
}

