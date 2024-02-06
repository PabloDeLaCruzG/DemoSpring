package com.pablodlc.appspring.interceptor;

import java.time.LocalTime;
import org.springframework.web.servlet.HandlerInterceptor;

public class InterceptorSchedule implements HandlerInterceptor {

    private static final LocalTime INICIO = LocalTime.of(15, 0);
    private static final LocalTime FIN = LocalTime.of(6, 0);

    @Override
    public boolean preHandle(@SuppressWarnings("null") jakarta.servlet.http.HttpServletRequest request,
            @SuppressWarnings("null") jakarta.servlet.http.HttpServletResponse response,
            @SuppressWarnings("null") Object handler) throws Exception {
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isAfter(INICIO) || horaActual.isBefore(FIN)) {
            response.getWriter().write("ESTAMOS EN MANTENIMIENTO. PRUEBE A ENTRAR MAS TARDE");
            return false;
        }
        return true;
    }

}