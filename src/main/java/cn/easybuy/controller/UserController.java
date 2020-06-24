package cn.easybuy.controller;


import cn.easybuy.pojo.User;
import cn.easybuy.service.IUserService;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author 钟佳慧
 * @since 2020-06-21
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户管理
     */
    @RequestMapping("user")
    public String user(Model model, @RequestParam(value = "action") String action
            , @RequestParam(value = "currentPage", required = false) Integer currentPage
            , @RequestParam(value = "rowPerPage", required = false) Integer rowPerPage
            , @RequestParam(value = "id", required = false) Integer id, HttpSession session
    ) {


        switch (action) {
            //用户信息
            case "index":
                model.addAttribute("user", session.getAttribute("loginUser"));
                model.addAttribute("menu", 2);
                return "/backend/user/userInfo";
            //分页查询用户信息
            case "queryUserList":
                int total = userService.count();
                currentPage = currentPage == null ? 1 : currentPage;
                rowPerPage = rowPerPage == null ? 10 : rowPerPage;
                Pager pager = new Pager(total, rowPerPage, currentPage);
                List<User> userList = userService.getUserList(currentPage, rowPerPage);
                pager.setUrl("admin/user?action=queryUserList");
                model.addAttribute("userList", userList);
                model.addAttribute("pager", pager);
                model.addAttribute("menu", 8);
                return "backend/user/userList";
            //更新用户信息
            case "toUpdateUser":
                User user = userService.getUser(id);
                model.addAttribute("user", user);
                return "/backend/user/toUpdateUser";

            default:
                return "";
        }

    }

    /**
     * 添加用户
     */
    @GetMapping("user/AddUser")
    public String addUser() {
        return "/backend/user/toUpdateUser";
    }


    /**
     * 根据id修改用户信息 或者添加用户
     */
    @PostMapping("user/updateUser")
    @ResponseBody
    public ReturnResult toUpdateUser(User user) {
        ReturnResult result = new ReturnResult();
        boolean is;

        if (user.getId() != null) {
            is = userService.update(user);
        } else {
            if (userService.selectIsName(user.getLoginName())) {
                return result.returnFail("用户已经存在");
            }
            is = userService.add(user);

        }
        if (is)
            result.returnSuccess();
        else {
            if (user.getId() == null) {
                return result.returnFail("增加失败！");
            } else {
                return result.returnFail("修改失败！");
            }
        }
        return result;
    }

    /**
     * 删除用户
     */
    @PostMapping("user/deleteUserById")
    @ResponseBody
    public ReturnResult deleteUserById(@RequestParam(value = "id", required = false) Integer id) {
        ReturnResult result = new ReturnResult();
        if (userService.deleteUserById(id))
            return result.returnSuccess();
        return result.returnFail("失败");
    }


}

