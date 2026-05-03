package com.universidad.antipatrones;

/*
 * Análisis del antipatrón God Object en la clase GestorBiblioteca.
 *
 * Esta clase concentra demasiadas responsabilidades y por eso tiene
 * múltiples razones para cambiar:
 *
 * Responsabilidad 1: Gestión del catálogo de libros
 * - Agregar libros
 * - Buscar libros
 * - Listar libros disponibles
 *
 * Responsabilidad 2: Gestión de socios
 * - Registrar socios
 * - Validar emails
 * - Verificar si un socio existe
 *
 * Responsabilidad 3: Gestión de préstamos
 * - Realizar préstamos
 * - Devolver libros
 * - Cambiar el estado de disponibilidad del libro
 *
 * Responsabilidad 4: Generación de reportes del sistema
 * - Imprimir el resumen general de libros, socios y préstamos
 *
 * Conclusión:
 * La clase tiene 4 razones principales para cambiar, por lo que viola
 * el principio de responsabilidad única (SRP) y presenta el antipatrón God Object.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// GOD OBJECT — clase que concentra todas las responsabilidades del sistema
public class GestorBiblioteca {
 // ---- Datos del sistema (3 dominios mezclados) ----
 private List<String[]> libros = new ArrayList<>(); // [id, titulo, autor, disponible]
 private List<String[]> socios = new ArrayList<>(); // [id, nombre, email]
 private List<String[]> prestamos = new ArrayList<>();// [libroId, socioId, fecha]
 // ---- Responsabilidad 1: Gestion de libros ----
 public void agregarLibro(String id, String titulo, String autor) {
 libros.add(new String[]{id, titulo, autor, "true"});
 System.out.println("Libro agregado: " + titulo);
 }
 public Optional<String[]> buscarLibro(String id) {
 return libros.stream().filter(l -> l[0].equals(id)).findFirst();
 }
 public List<String[]> listarLibrosDisponibles() {
 return libros.stream().filter(l -> l[3].equals("true")).toList();
 }
 // ---- Responsabilidad 2: Gestion de socios ----
 public void registrarSocio(String id, String nombre, String email) {
 if (!email.contains("@")) {
 System.out.println("Email invalido");
 return;
 }
 socios.add(new String[]{id, nombre, email});
 System.out.println("Socio registrado: " + nombre);
 }
 public boolean socioExiste(String id) {
 return socios.stream().anyMatch(s -> s[0].equals(id));
 }
 // ---- Responsabilidad 3: Gestion de prestamos ----
 public void realizarPrestamo(String libroId, String socioId) {
 Optional<String[]> libro = buscarLibro(libroId);
 if (libro.isEmpty()) { System.out.println("Libro no encontrado");
return; }
 if (!libro.get()[3].equals("true")) { System.out.println("Librono disponible"); return; }
 if (!socioExiste(socioId)) { System.out.println("Socio no registrado"); return; }
 libro.get()[3] = "false";
 prestamos.add(new String[]{libroId, socioId, "2026-03-17"});
 System.out.println("Prestamo realizado: libro " + libroId + " a socio " + socioId);
 }
 public void devolverLibro(String libroId) {
 Optional<String[]> libro = buscarLibro(libroId);
 libro.ifPresent(l -> { l[3] = "true"; System.out.println("Libro devuelto: " + l[1]); });
 }
 // ---- Responsabilidad 4: Reportes ----
 public void imprimirReporteCompleto() {
 System.out.println("=== REPORTE BIBLIOTECA ===");
 System.out.println("Libros registrados: " + libros.size());
 System.out.println("Libros disponibles: " +
listarLibrosDisponibles().size());
 System.out.println("Socios registrados: " + socios.size());
 System.out.println("Prestamos activos: " + prestamos.size());
 System.out.println("==========================");
 }

}
 