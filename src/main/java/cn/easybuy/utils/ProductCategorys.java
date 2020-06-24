package cn.easybuy.utils;

import cn.easybuy.pojo.ProductCategory;
import lombok.Data;

@Data
public class ProductCategorys {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer type;
    private String iconClass;
    private String parentName;

    public ProductCategorys(ProductCategory productCategory, String parentName) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.parentId = productCategory.getParentId();
        this.type = productCategory.getType();
        this.iconClass = productCategory.getIconClass();
        this.parentName = parentName;
    }
}
