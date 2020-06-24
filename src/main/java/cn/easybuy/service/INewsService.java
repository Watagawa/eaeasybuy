package cn.easybuy.service;

import cn.easybuy.pojo.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 新闻服务类
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
public interface INewsService extends IService<News> {
    /**
     * 添加新闻
     *
     * @param news
     * @
     */
    public void add(News news);

    /**
     * 修改新闻
     *
     * @param news
     * @
     */
    public void update(News news);

    /**
     * 根据id删除新闻
     *
     * @param id
     * @
     */
    public void deleteById(Integer id);

    /**
     * 根据id查询新闻
     *
     * @return
     * @
     */
    public News getNewsById(Integer id);

    /**
     * 查询新闻列表
     *
     * @return
     * @
     */
    public List<News> queryNewsList(Integer from, Integer pageSize);

    /**
     * 查询新闻的数目
     *
     * @return
     * @
     */
    public Integer queryNewsCount();
}