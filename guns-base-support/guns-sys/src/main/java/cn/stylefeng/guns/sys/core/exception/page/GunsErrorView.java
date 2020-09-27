package cn.stylefeng.guns.sys.core.exception.page;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 错误页面的默认跳转(例如请求404的时候,默认走这个视图解析器)
 * @author fengshuonan
 * @date 2017-05-21 11:34
*/
public class GunsErrorView implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(final Map<String, ?> map,final HttpServletRequest request,final HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/global/error").forward(request, response);
    }
}