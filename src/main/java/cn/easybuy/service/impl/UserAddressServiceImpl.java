package cn.easybuy.service.impl;

import cn.easybuy.pojo.UserAddress;
import cn.easybuy.mapper.UserAddressMapper;
import cn.easybuy.service.IUserAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户地址服务实现类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-21
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {


    @Override
    public List<UserAddress> queryUserAddressList(Integer id) {
        QueryWrapper<UserAddress> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Integer add(UserAddress userAddress) {
        return baseMapper.insert(userAddress);
    }

    @Override
    public UserAddress getUserAddressById(Integer addressId) {
        return baseMapper.selectById(addressId);
    }
}
