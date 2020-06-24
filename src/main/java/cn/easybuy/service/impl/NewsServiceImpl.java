package cn.easybuy.service.impl;

import cn.easybuy.pojo.News;
import cn.easybuy.mapper.NewsMapper;
import cn.easybuy.service.INewsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 新闻服务实现类
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Override
    public void add(News news) {
        baseMapper.insert(news);
    }

    @Override
    public void update(News news) {
        baseMapper.updateById(news);
    }

    @Override
    public void deleteById(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public News getNewsById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<News> queryNewsList(Integer from, Integer pageSize) {
        Page<News> page = new Page<>(from, pageSize);
        baseMapper.selectPage(page, null);
        return page.getRecords();
    }

    @Override
    public Integer queryNewsCount() {
        return baseMapper.selectCount(null);
    }
}
