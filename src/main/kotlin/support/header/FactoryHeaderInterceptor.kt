package support.header

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class FactoryHeaderInterceptor: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val globalTxId = request.getHeader(FactoryHeader.GLOBAL_TRANSACTION_ID_HEADER)

        FactoryRequestHolder.set(
            FactoryHeader(globalTxId)
        )

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        FactoryRequestHolder.clear()
    }
}
