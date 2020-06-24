package cn.easybuy.controller;


import cn.easybuy.pojo.Guestbook;
import cn.easybuy.service.IGuestbookService;
import cn.easybuy.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 留言博前端控制器
 * </p>
 *
 * @author 贺宗
 * @since 2020-06-23
 */
@Controller
@RequestMapping("")
public class GuestBookController {
    @Autowired
    IGuestbookService guestbookService;

    @RequestMapping("guestbook/index")
    //显示留言博
    public String guestbook() {
        return "/pre/guestbook/guestbook";
    }


    /**
     * 用户添加留言
     *
     * @param remake
     * @param title
     * @return
     */
    @ResponseBody
    @PostMapping("guestbook/addGuest")
    public String addGuest(String remake, String title) {
        try {
            guestbookService.addGuest(remake, title);
        } catch (Exception e) {
            e.printStackTrace();
            return "留言失败";
        }
        return "留言成功";
    }


    @RequestMapping("admin/guestbook")
    public ModelAndView admin(@RequestParam(defaultValue = "1") Integer currentPageNo, @RequestParam(defaultValue = "6") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        int guestbookCount = guestbookService.count();
        Pager pager = new Pager(guestbookCount, pageSize, currentPageNo);
        pager.setUrl("admin/guestbook/admin");
        modelAndView.addObject("guestbookList", guestbookService.selectAllGuest(currentPageNo, pageSize));
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("menu", 10);
        modelAndView.setViewName("/backend/guestbook/guestbook");

        return modelAndView;
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("guestbook/delectGuestById")
    public String delectGuestById(Integer id) {
        try {
            guestbookService.delectGuestById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
        return "删除成功";
    }

    @ResponseBody
    @GetMapping("guestbook/getGuestById")
    public String getGuestById(int id) {
        try {
            return guestbookService.selectGuestById(id).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过标题查询留言
     *
     * @param Title
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @PostMapping("guestbook/selectGuestByTitle")
    public List<Guestbook> selectGuestByTitle(String Title, @RequestParam(defaultValue = "1") Integer currentPageNo, @RequestParam(defaultValue = "8") Integer pageSize) {
        List<Guestbook> guestbooks = null;
        try {
            guestbookService.selectGuestByTitle(Title, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guestbooks;
    }

}
