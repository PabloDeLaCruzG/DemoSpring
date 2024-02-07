package com.pablodlc.appspring.interceptor;

import java.time.LocalTime;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InterceptorSchedule implements HandlerInterceptor {

    // Definición de las horas de inicio y fin del mantenimiento
    private static final LocalTime INICIO = LocalTime.of(23, 0);
    private static final LocalTime FIN = LocalTime.of(6, 0);

    // Método que se ejecuta antes de manejar la solicitud HTTP
    @SuppressWarnings("null")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LocalTime horaActual = LocalTime.now();
        // Comprueba si la hora actual está dentro del horario de mantenimiento
        if (horaActual.isAfter(INICIO) || horaActual.isBefore(FIN)) {
            // Si estamos dentro del horario de mantenimiento, envía un mensaje de error al
            // cliente
            response.getWriter().write("ESTAMOS EN MANTENIMIENTO. PRUEBE A ENTRAR MAS TARDE");
            // Devuelve falso para indicar que la solicitud no debe ser manejada
            return false;
        }
        // Si no estamos en el horario de mantenimiento, permite que la solicitud sea
        // manejada normalmente
        return true;
    }
}