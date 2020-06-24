package cn.easybuy.service.impl;

import cn.easybuy.mapper.GuestbookMapper;
import cn.easybuy.pojo.Guestbook;
import cn.easybuy.service.IGuestbookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 留言博服务实现类
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-23
 */
@Service
public class GuestbookServiceImpl extends ServiceImpl<GuestbookMapper, Guestbook> implements IGuestbookService {

    @Override
    public void addGuest(String remake, String title) {
        Guestbook guestbook = new Guestbook();
        guestbook.setRemake(remake);
        guestbook.setTitle(title);
        baseMapper.insert(guestbook);
    }

    @Override
    public List<Guestbook> selectAllGuest(int from, int size) {
        Page<Guestbook> page = new Page<>(from, size);
        baseMapper.selectPage(page, null);
        return page.getRecords();
    }

    @Override
    public List<Guestbook> selectGuestByTitle(String Title, int from, int size) {
        Page<Guestbook> page = new Page<>(from, size);
        QueryWrapper<Guestbook> guestbookQueryWrapper = new QueryWrapper<>();
        guestbookQueryWrapper.like("title", Title);
        baseMapper.selectPage(page, guestbookQueryWrapper);
        return page.getRecords();
    }

    @Override
    public int count() {
        return baseMapper.selectCount(null);
    }

    @Override
    public void delectGuestById(int id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Guestbook selectGuestById(int id) {
        return baseMapper.selectById(id);
    }
}
