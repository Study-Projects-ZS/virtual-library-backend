package com.studygroupzs.virtuallibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.studygroupzs.virtuallibrary.model.User;
import com.studygroupzs.virtuallibrary.model.UserLogin;
import com.studygroupzs.virtuallibrary.repository.UserRepository;
import com.studygroupzs.virtuallibrary.security.JwtService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

		public Optional<User> createUser(User user) {

			if (userRepository.findByUsername(user.getUsername()).isPresent())
				return Optional.empty();

			user.setPassaword(encryptPassword(user.getPassaword()));

			return Optional.of(userRepository.save(user));
		
		}

		public Optional<User> updateUser(User user) {
			
			if(userRepository.findById(user.getId()).isPresent()) {

				Optional<User> buscaUsuario = userRepository.findByUsername(user.getUsername());

				if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != user.getId()))
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

				user.setPassaword(null);

				return Optional.ofNullable(userRepository.save(user));
				
			}

			return Optional.empty();
		
		}	

		public Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin) {
	        
	        // Gera o Objeto de autenticação
			var credenciais = new UsernamePasswordAuthenticationToken(userLogin.get().getUser(), userLogin.get().get());
			
	        // Autentica o Usuario
			Authentication authentication = authenticationManager.authenticate(credenciais);
	        
	        // Se a autenticação foi efetuada com sucesso
			if (authentication.isAuthenticated()) {

	            // Busca os dados do usuário
				Optional<User> usuario = userRepository.findByUsuario(userLogin.get().getUser());

	            // Se o usuário foi encontrado
				if (usuario.isPresent()) {

	                // Preenche o Objeto usuarioLogin com os dados encontrados 
				   userLogin.get().setId(usuario.get().getId());
	                userLogin.get().setNome(usuario.get().getNome());
	                userLogin.get().setFoto(usuario.get().getFoto());
	                userLogin.get().setSobre(usuario.get().getSobre());
	                userLogin.get().setToken(generateToken(userLogin.get().getUser()));
	                userLogin.get().setSenha("");
					
	                 // Retorna o Objeto preenchido
				   return userLogin;
				
				}

	        } 
	            
			return Optional.empty();

	    }

		// Criptografia da senha
		private String encryptPassword(String password) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			return encoder.encode(password);

		}

		// Geração do Token Bearer
		private String generateToken(String user) {
			return "Bearer " + jwtService.generateToken(user);
		}
}
