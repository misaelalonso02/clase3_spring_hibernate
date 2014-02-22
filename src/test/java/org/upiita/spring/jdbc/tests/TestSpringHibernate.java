package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		
		UsuarioDAO usuarioDAO= (UsuarioDAO) contexto.getBean("usuarioDAO");
		
		Usuario usuario = new Usuario();
		
		usuario.setUsuarioId(3);
		usuario.setNombre("Misael");
		usuario.setEmail("misael@hotmail.com");
		usuario.setPassword("12345678");
		usuarioDAO.creaUsuario(usuario);
		///Actualizar
		usuario.setPassword("mimoso");
		usuarioDAO.creaUsuario(usuario);
		
		
		System.out.println("datos guardar");
		
		
		////Consultar en la base de datos
		Usuario usuarioBD=usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("Consulta:");
		
		System.out.println("ID:"+usuarioBD.getUsuarioId());
		System.out.println("Nombre:"+usuarioBD.getNombre());
		System.out.println("Password:"+usuarioBD.getPassword());
		System.out.println("Email:"+usuarioBD.getEmail());
		
		
		////Consulta usuarios por criterios
		
		Usuario usuarioCriterio=usuarioDAO.buscaPorEmailYPassword("misael@hotmail.com", "mimoso9");
		System.out.println("Consulta Criterio:"+usuarioCriterio);
		
		System.out.println("ID:"+usuarioCriterio.getUsuarioId());
		System.out.println("Nombre:"+usuarioCriterio.getNombre());
		System.out.println("Password:"+usuarioCriterio.getPassword());
		System.out.println("Email:"+usuarioCriterio.getEmail());
		
		
		////Prueba Like
		
		List<Usuario> usuarios=usuarioDAO.buscaPorNombre("z");
		
		System.out.println("usuarios por nombre:"+usuarios);
	}

}

