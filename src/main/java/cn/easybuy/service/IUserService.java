package cn.easybuy.service;

import cn.easybuy.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
public interface IUserService extends IService<User> {
    /**
     * 添加用户
     *
     * @param user 待添加的用户
     * @return 是否添加成功
     */
    public boolean add(User user);

    /**
     * 更新用户
     *
     * @param user 需要更新的用户
     * @return 是否成功
     */
    public boolean update(User user);

    /**
     * 根据id删除用户
     *
     * @param userId 删除用户id
     * @return 是否成功
     */
    public boolean deleteUserById(Integer userId);

    /**
     * 根据id获取用户
     *
     * @param userId 用户id
     * @return 返回用户
     */
    public User getUser(Integer userId);

    /**
     * 根据用登录获取用户
     *
     * @param loginName 登录名
     * @return 返回用户
     */
    public User getUser(String loginName);

    /**
     * 分页查询用户列表
     *
     * @param currentPageNo 第几页
     * @param pageSize      页大小
     * @return 用户列表
     */
    public List<User> getUserList(Integer currentPageNo, Integer pageSize);

    /**
     * 获取用户数量
     *
     * @return 用户数量
     */
    public int count();

    /**
     * 判断用户已经存在
     *
     * @param loginName 登录名
     * @return 是否存在
     */
    public boolean selectIsName(String loginName);
}
