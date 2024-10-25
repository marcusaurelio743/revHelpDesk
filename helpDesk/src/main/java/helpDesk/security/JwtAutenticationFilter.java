package helpDesk.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import helpDesk.domain.dtos.CredenciaisDTO;

public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;

	public JwtAutenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken autenticationToken = 
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(),new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(autenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token  = jwtUtil.gerenarateToken(username);
		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader( "Authorization","Bearer "+token);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}
	
	private CharSequence json() {
		Long data = new Date().getTime();
		return "{"+ " Timestemp: "+data+" , " +" status 401 "+ "Error de autenticação";
	}
}
