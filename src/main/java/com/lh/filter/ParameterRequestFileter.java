package com.lh.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 梁昊
 * @date:2019/8/28
 */
public class ParameterRequestFileter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        https://blog.csdn.net/gaoleijie/article/details/79446361
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
