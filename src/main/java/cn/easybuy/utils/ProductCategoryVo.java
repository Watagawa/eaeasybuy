// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProductCategoryVo.java

package cn.easybuy.utils;

import cn.easybuy.pojo.Product;
import cn.easybuy.pojo.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVo {

    private ProductCategory productCategory;
    private List<ProductCategoryVo> productCategoryVoList;
    private List<Product> productList;
}
