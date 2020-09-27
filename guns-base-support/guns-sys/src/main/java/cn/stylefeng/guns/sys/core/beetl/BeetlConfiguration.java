package cn.stylefeng.guns.sys.core.beetl;

import cn.stylefeng.guns.base.auth.context.LoginContext;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.i18n.context.UserTranslationContext;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author stylefeng
 * @Date 2018/2/22 21:03
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    private LoginContext loginContext;

    public BeetlConfiguration(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", loginContext);
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("constants", new ConstantsContext());
        groupTemplate.registerFunctionPackage("lang", new UserTranslationContext());
    }
}
