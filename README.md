# Refactoring Lab — Antipatrón God Object

## 📌 Descripción del proyecto

Este proyecto corresponde al laboratorio de la Unidad 6 de Antipatrones de Diseño, cuyo objetivo es refactorizar una clase que presenta el antipatrón **God Object**, aplicando el **Principio de Responsabilidad Única (SRP)**.

El sistema simula la gestión básica de una biblioteca: libros, socios, préstamos y reportes.

---

## ⚠️ Antipatrón identificado: God Object

La clase `GestorBiblioteca` presenta el antipatrón **God Object**, ya que concentra múltiples responsabilidades en una sola clase, generando:

- Alto acoplamiento
- Baja cohesión
- Dificultad de mantenimiento
- Baja escalabilidad

---

## 🔍 Análisis de responsabilidades

La clase `GestorBiblioteca` tiene **4 razones distintas para cambiar**, lo que viola el principio SRP:

1. **Gestión del catálogo de libros**
   - Agregar libros
   - Buscar libros
   - Listar disponibilidad

2. **Gestión de socios**
   - Registrar socios
   - Validar email
   - Buscar socios

3. **Gestión de préstamos**
   - Prestar libros
   - Devolver libros
   - Controlar disponibilidad

4. **Generación de reportes**
   - Reporte general del sistema

---

## 🧠 Solución aplicada: Principio de Responsabilidad Única (SRP)

Se refactorizó el sistema dividiendo la clase original en múltiples clases especializadas, cada una con una única responsabilidad.

---

## 🏗️ Nueva arquitectura

### 📦 Clases de dominio
- `Libro`
- `Socio`

Estas reemplazan los `String[]`, mejorando la claridad y tipado del sistema.

---

### ⚙️ Clases especializadas

- **CatalogoLibros**
  - Gestión de libros

- **RegistroSocios**
  - Gestión de socios

- **ServicioPrestamos**
  - Gestión de préstamos
  - Usa inyección de dependencias

- **GeneradorReportes**
  - Generación de reportes

---

## 🔗 Inyección de dependencias

Se implementa inyección por constructor en:

- `ServicioPrestamos`
- `GeneradorReportes`

Esto mejora:
- Testabilidad
- Desacoplamiento
- Mantenibilidad

---


## 📸 Evidencia

### Antes (God Object)
<img width="1516" height="916" alt="image" src="https://github.com/user-attachments/assets/9ce83bf8-a594-467b-a4d6-9812fbd4317b" />


### Después (Refactorizado)
<img width="1402" height="992" alt="image" src="https://github.com/user-attachments/assets/f207e5a5-120c-4bff-85de-db1f18cd515f" />




## ▶️ Ejecución del proyecto

### Requisitos:
- Java 17+
- Maven 3.8+

### Comando para ejecutar:

```bash
mvn compile
mvn exec:java
