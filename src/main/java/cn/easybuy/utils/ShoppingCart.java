package cn.easybuy.utils;

import cn.easybuy.pojo.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ShoppingCart implements Serializable {
    public List<ShoppingCartItem> items = new ArrayList<>();
    private Double sum;

    //添加一项
    public ReturnResult addItem(Product product, Integer quantity) {
        ReturnResult result = new ReturnResult();
        int flag = 0;
        for (int i = 0; i < items.size(); i++) {
            //判断购物车中是否已有此商品，如果有则累计数量
            if ((items.get(i).getProduct().getId()).equals(product.getId())) {
                if (items.get(i).getQuantity() + quantity > product.getStock()) {
                    return result.returnFail("商品数量不足");
                } else {
                    items.get(i).setQuantity(items.get(i).getQuantity() + quantity);
                    flag = 1;
                }
            }
        }
        if (quantity > product.getStock()) {
            return result.returnFail("商品数量不足");
        }
        if (flag == 0) {
            items.add(new ShoppingCartItem(product, quantity));
        }
        getTotalCost();
        return result.returnSuccess();
    }

    //移除一项
    public void removeItem(int index) {
        Optional<ShoppingCartItem> first = items.stream().filter(x -> x.getProduct().getId() == index).findFirst();
        first.ifPresent(shoppingCartItem -> items.remove(shoppingCartItem));
        getTotalCost();
    }

    //修改数量
    public void modifyQuantity(int index, Integer quantity) {
        Optional<ShoppingCartItem> first = items.stream().filter(x -> x.getProduct().getId() == index).findFirst();
        first.ifPresent(shoppingCartItem -> shoppingCartItem.setQuantity(quantity));
        getTotalCost();
    }

    //计算总价格
    public Double getTotalCost() {
        sum = items.stream().mapToDouble(ShoppingCartItem::getCost).sum();
        return sum;
    }

}
