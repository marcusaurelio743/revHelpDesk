package helpDesk.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAutorizationFilter extends BasicAuthenticationFilter{
	private JwtUtil jwtUtil;
	private UserDetailsService service;
	
	public JwtAutorizationFilter(AuthenticationManager authenticationManager,JwtUtil jwtUtil,UserDetailsService service) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.service = service;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer ")) {
			
			UsernamePasswordAuthenticationToken authToken = getAutentication(header.substring(7));
			System.out.println("chegou!!!");
			
			
			if(authToken != null) {
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		chain.doFilter(request, response);
		
	}

	private UsernamePasswordAuthenticationToken getAutentication(String token) {
		System.out.println("chegou no getAuthtentication");
		if(jwtUtil.tokenValido(token)) {
			System.out.println("token valido");
			String username = jwtUtil.getUsername(token);
			UserDetails details = service.loadUserByUsername(username);
			System.out.println("username: "+username);
			
			
			return new UsernamePasswordAuthenticationToken(details.getUsername(), null,details.getAuthorities());
		}
		return null;
	}

}
