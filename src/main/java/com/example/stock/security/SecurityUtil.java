package com.example.stock.security;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.stock.StockApplication;
import com.example.stock.bean.User;
import com.example.stock.service.facade.UserService;

@Service
public class SecurityUtil {
    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    @Autowired
    private static ApplicationContext applicationContext;

    public static User getCurrentUser() {
        UserService userService= StockApplication.getCntx().getBean(UserService.class);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object user = securityContext.getAuthentication().getPrincipal();
        System.out.println(user);
        if (user instanceof String) {
            return userService.findByUsername((String) user);
        } else if (user instanceof User) {
            return (User) user;
        } else {
            return null;
        }
//        return (User)Optional.ofNullable(securityContext.getAuthentication())
//                .map(authentication -> {
//                    if (authentication.getPrincipal() instanceof UserDetails) {
//                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
//                        return springSecurityUser;
////                        return springSecurityUser.getUsername();
//                    }
////                    else if (authentication.getPrincipal() instanceof String) {
////                        return (String) authentication.getPrincipal();
////                    }
//                    return null;
//                }).get();
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null &&
                getAuthorities(authentication).anyMatch(authority::equals);
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority);
    }

}