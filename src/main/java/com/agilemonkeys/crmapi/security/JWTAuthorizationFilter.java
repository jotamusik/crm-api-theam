package com.agilemonkeys.crmapi.security;

import com.agilemonkeys.crmapi.model.entity.User;
import com.agilemonkeys.crmapi.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.agilemonkeys.crmapi.security.Constants.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, ApplicationContext ctx) {
        super(authManager);
        userService = ctx.getBean(UserService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZACION_KEY);

        if (token == null) {
            return null;
        }

        String username = Jwts.parser()
                .setSigningKey(SUPER_SECRET_KEY)
                .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
                .getBody()
                .getSubject();

        if (username == null){
            return null;
        }

        User user = userService.getUserByUsername(username);

        if (user == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

}
