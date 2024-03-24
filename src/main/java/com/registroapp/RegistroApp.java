package com.registroapp;

import com.registroapp.modelo.Estudiante;
import com.registroapp.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class RegistroApp implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(RegistroApp.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		SpringApplication.run(RegistroApp.class, args);
		logger.info("Aplicación finalizada...");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando método RUN de Spring..." + nl);
		var salir = false;
		var consola = new Scanner(System.in);

		while (!salir) {
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu() {
		logger.info(nl);
		logger.info("""
                ***** Sistema de Estudiantes *****
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Actualizar Estudiante
                5. Eliminar Estudiante
                6. Salir
                """);
	}

	private boolean ejecutarOpciones(Scanner consola) {
		logger.info("Elige una opción: ");
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info("Listado de Estudiantes..." + nl);
				List<Estudiante> estudiantes = estudianteServicio.listar();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString())));
			}
			case 2 -> {
				logger.info(nl + "Introduce el id_estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarPorId(idEstudiante);

				if (estudiante != null) {
					logger.info("Estudiante encontrado: " + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado: " + estudiante + nl);
				}
			}
			case 3 -> {
				logger.info("Agregar Estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardar(estudiante);
				logger.info("Estudiante agregado:" + estudiante + nl);
			}
			case 4 -> {
				logger.info("Actualizar Estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarPorId(idEstudiante);
				if (estudiante != null) {
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardar(estudiante);
					logger.info("Estudiante actualizado:" + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado: " + estudiante + nl);
				}
			}
			case 5 -> {
				logger.info("Eliminar Estudiante: " + nl);
				logger.info("Id Estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteServicio.buscarPorId(idEstudiante);
				if (estudiante != null) {
					estudianteServicio.eliminar(estudiante);
					logger.info("Estudiante eliminado:" + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado: " + estudiante + nl);
				}
			}
			case 6 -> {
				logger.info("Hasta Pronto!" + nl + nl);
				salir = true;
			}
			default -> logger.info("Opción no reconocida: " + opcion + nl);
		}
		return salir;
	}
}
