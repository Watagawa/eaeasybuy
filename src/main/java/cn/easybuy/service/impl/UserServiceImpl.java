package cn.easybuy.service.impl;

import cn.easybuy.pojo.User;
import cn.easybuy.mapper.UserMapper;
import cn.easybuy.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean add(User user) {
        return baseMapper.insert(user) == 1;
    }

    @Override
    public boolean update(User user) {
        return baseMapper.updateById(user) == 1;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return baseMapper.deleteById(id) == 1;
    }


    @Override
    public List<User> getUserList(Integer from, Integer pageSize) {
        Page<User> page = new Page<>(from, pageSize);
        baseMapper.selectPage(page, null);
        return page.getRecords();
    }

    @Override
    public int count() {
        return baseMapper.selectCount(null);
    }

    @Override
    public boolean selectIsName(String loginName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginName", loginName);
        return !baseMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    public User getUser(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public User getUser(String loginName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginName", loginName);
        return baseMapper.selectOne(queryWrapper);
    }
}
