package cn.easybuy.service;

import cn.easybuy.pojo.Guestbook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 留言博服务类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-23
 */
public interface IGuestbookService extends IService<Guestbook> {

    /**
     * 添加留言
     */
    public void addGuest(String remake, String title);

    /**
     * 分页查询留言
     */
    public List<Guestbook> selectAllGuest(int from, int size);

    /**
     * 根据留言标题查询留言
     */
    public List<Guestbook> selectGuestByTitle(String Title, int from, int size);

    /**
     * 留言数量
     */
    public int count();

    /**
     * 删除留言
     */
    public void delectGuestById(int id);

    /**
     * 根据id查询留言
     */
    public Guestbook selectGuestById(int id);

}
