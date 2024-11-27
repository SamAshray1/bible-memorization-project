package com.bibleapp.bibleproject.filters;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class TransactionIdFilter implements Filter {

    private static final String TRANSACTION_ID_HEADER = "X-Transaction-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String transactionId = httpRequest.getHeader(TRANSACTION_ID_HEADER);
        if (transactionId == null || transactionId.isEmpty()) {
            transactionId = UUID.randomUUID().toString();
        }

        // Add transaction ID to MDC (for logging)
        MDC.put("transactionId", transactionId);

        // Propagate transaction ID for response headers
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setBufferSize(512);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear(); // Clear MDC after request is processed
        }
    }
}
