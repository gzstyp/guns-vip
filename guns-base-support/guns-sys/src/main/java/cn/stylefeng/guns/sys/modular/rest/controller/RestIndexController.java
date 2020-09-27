package cn.stylefeng.guns.sys.modular.rest.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.sys.modular.rest.service.RestUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.stylefeng.guns.base.auth.exception.enums.AuthExceptionEnum.NO_ROLE_ERROR;

/**
 * 首页信息
 *
 * @author fengshuonan
 * @Date 2019/7/24 22:09
 */
@RestController
@RequestMapping("/rest")
public class RestIndexController extends BaseController {

    @Resource
    private RestUserService restUserService;

    /**
     * 获取用户菜单列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public ResponseData menus() {

        //获取当前用户角色列表
        LoginUser user = LoginContextHolder.getContext().getUser();
        List<Long> roleList = user.getRoleList();

        //如果角色为空
        if (roleList == null || roleList.size() == 0) {
            return new ErrorResponseData(NO_ROLE_ERROR.getCode(), NO_ROLE_ERROR.getMessage());
        }

        //渲染菜单
        return new SuccessResponseData(restUserService.getUserMenuNodes(roleList));
    }

    /**
     * 获取用户菜单列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public ResponseData getUserInfo() {
        LoginUser user = LoginContextHolder.getContext().getUser();
        return new SuccessResponseData(user);
    }

}
