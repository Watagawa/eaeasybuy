package cn.easybuy.service;

import cn.easybuy.pojo.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户地址服务类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */
public interface IUserAddressService extends IService<UserAddress> {
    /**
     * 根据用户id 查询用户地址
     */
    public List<UserAddress> queryUserAddressList(Integer id);

    /**
     * 给用户添加地址
     */
    public Integer add(UserAddress userAddress);

    /**
     * 根据id查询地址
     */
    public UserAddress getUserAddressById(Integer addressId);
}
