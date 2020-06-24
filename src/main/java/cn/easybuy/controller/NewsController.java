package cn.easybuy.controller;


import cn.easybuy.pojo.News;
import cn.easybuy.service.INewsService;
import cn.easybuy.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 新闻前端控制器
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
@Controller
@RequestMapping("/admin")
public class NewsController {

    @Autowired
    private INewsService newsService;

    /**
     * 新闻
     *
     */
    @RequestMapping("news")
    public String news(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "id", required = false) Integer id
    ) {


        switch (action) {
            //新闻列表
            case "queryNewsList":

                currentPage = currentPage == null ? 1 : currentPage;
                pageSize = pageSize == null ? 10 : pageSize;
                int newsCount = newsService.queryNewsCount();

                Pager pager = new Pager(newsCount, pageSize, currentPage);
                pager.setUrl("admin/news?action=queryNewsList");

                List<News> newsList = newsService.queryNewsList(currentPage, pageSize);
                model.addAttribute("newsList", newsList);
                model.addAttribute("pager", pager);
                model.addAttribute("menu", 7);
                return "/backend/news/newsList";
            //新闻详情
            case "newsDeatil":
                News news = newsService.getNewsById(id);
                model.addAttribute("news", news);
                model.addAttribute("menu", 7);
                return "/backend/news/newsDetail";
            default:
                return "redirect:Home";
        }
    }
}

