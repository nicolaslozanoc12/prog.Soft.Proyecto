# 🏭 Sistema de Gestión de Calidad y Reposición - Toyota

[![Java](https://img.shields.io/badge/Java-21+-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-6DB33F?style=flat&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Camunda](https://img.shields.io/badge/Camunda-7.23.0-FC5D0D?style=flat&logo=camunda&logoColor=white)](https://camunda.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-336791?style=flat&logo=postgresql&logoColor=white)](https://postgresql.org/)

## 📋 Descripción

Sistema integrado para la gestión de control de calidad y reposición de insumos en líneas de producción industrial. El proyecto está compuesto por dos módulos principales que trabajan en conjunto para automatizar los procesos de negocio relacionados con el control de calidad y la gestión de inventario.

## 🏗️ Arquitectura del Sistema

```
┌─────────────────┐       REST API        ┌──────────────────┐
│    AppWeb       │◄────────────────────►│   bpm-engine     │
│   (Frontend)    │   http://localhost    │  (BPM Engine)    │
│   Puerto: 9001  │       :9000           │   Puerto: 9000   │
└─────────────────┘                       └──────────────────┘
        │                                          │
        │                                          │
        ▼                                          ▼
┌─────────────────┐                       ┌──────────────────┐
│   Thymeleaf     │                       │    PostgreSQL    │
│   Templates     │                       │    Database      │
└─────────────────┘                       └──────────────────┘
```

## 📁 Estructura del Proyecto

```
📦 prog.Soft.Proyecto-main/
├── 🌐 AppWeb/                          # Aplicación Web Frontend
│   ├── src/main/java/org/software1/appweb/
│   │   ├── 🎮 controllers/             # Controladores REST y Vistas
│   │   ├── 🔧 services/                # Servicios de negocio
│   │   ├── ⚙️ config/                  # Configuración Spring
│   │   └── 🚀 AppWebApplication.java   # Clase principal
│   ├── src/main/resources/
│   │   ├── 📄 templates/               # Vistas Thymeleaf
│   │   └── ⚙️ application.properties   # Configuración
│   └── 📦 pom.xml
├── 🔄 bpm-engine/                      # Motor de Procesos BPMN
│   ├── src/main/java/com/software1/
│   │   ├── 🎮 controllers/             # API Controllers
│   │   ├── 🔧 services/                # Servicios BPM
│   │   ├── 📊 models/                  # Modelos de datos
│   │   ├── 🗃️ repositories/            # Repositorios JPA
│   │   └── 🚀 Application.java         # Clase principal
│   ├── src/main/resources/
│   │   ├── 🔄 static.bpmn/             # Diagramas de proceso
│   │   ├── 📋 static.form/             # Formularios Camunda
│   │   ├── 🧠 static.dmn/              # Reglas de decisión
│   │   ├── ⚙️ application.properties   # Config PostgreSQL
│   │   └── ⚙️ application.yaml         # Config Camunda
│   └── 📦 pom.xml
└── 📖 README.md
```

## 🌐 AppWeb - Frontend

### ✨ Características
- **Interfaz Web Responsiva**: Desarrollada con Thymeleaf y Bootstrap
- **Gestión de Formularios**: Registro de hallazgos, anomalías e inventario
- **Integración BPM**: Comunicación directa con el motor Camunda
- **Reportes en Tiempo Real**: Visualización de estado de procesos y inventario

### 🛠️ Tecnologías
- **Framework**: Spring Boot 3.5.0
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Java Version**: 21

### 📋 Dependencias Principales
```xml
<!-- Spring Boot Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Thymeleaf Template Engine -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

### 🚀 Ejecución
```bash
cd AppWeb
./mvnw spring-boot:run
```
**URL**: http://localhost:9001

## 🔄 bpm-engine - Motor BPM

### ✨ Características
- **Motor Camunda 7.23.0**: Procesamiento de workflows BPMN
- **Procesos Automatizados**: Control de calidad, reposición y gestión de líneas
- **API REST**: Endpoints para integración con frontend
- **Persistencia**: Base de datos PostgreSQL para estado de procesos

### 🔄 Procesos Implementados
1. **Control de Calidad**: Detección y manejo de anomalías en producción
2. **Gestión de Reposición**: Automatización de solicitudes de insumos
3. **Inspección de Línea**: Monitoreo continuo de líneas de producción
4. **Solución de Problemas**: Workflow de escalamiento y resolución

### 🛠️ Tecnologías
- **Motor BPM**: Camunda Platform 7.23.0
- **Framework**: Spring Boot 3.4.4
- **Base de Datos**: PostgreSQL
- **ORM**: Spring Data JPA

### 📋 Dependencias Principales
```xml
<!-- Camunda BPM Engine -->
<dependency>
    <groupId>org.camunda.bpm.springboot</groupId>
    <artifactId>camunda-bpm-spring-boot-starter-rest</artifactId>
</dependency>

<!-- Camunda Web App -->
<dependency>
    <groupId>org.camunda.bpm.springboot</groupId>
    <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
</dependency>

<!-- PostgreSQL & JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 🚀 Ejecución
```bash
cd bpm-engine
./mvnw spring-boot:run
```
**URLs**:
- API REST: http://localhost:9000/engine-rest
- Camunda Cockpit: http://localhost:9000/camunda

**Credenciales por defecto**:
- Usuario: `demo`
- Contraseña: `demo`

## 🔗 Integración entre Módulos

### 🔄 Flujo de Comunicación
1. **Frontend (AppWeb)** → Formularios y dashboards para usuarios
2. **API REST** → Comunicación entre AppWeb y bpm-engine
3. **Motor BPM** → Procesamiento automático de workflows
4. **Base de Datos** → Persistencia de estado y datos

### 📡 Endpoints Principales
```
GET  /engine-rest/task                    # Obtener tareas pendientes
POST /engine-rest/process-definition/key/{key}/start  # Iniciar proceso
POST /engine-rest/task/{id}/complete      # Completar tarea
GET  /engine-rest/task/{id}/form-variables # Obtener variables de formulario
```

## 🛠️ Configuración

### 📄 AppWeb (`application.properties`)
```properties
spring.application.name=AppWeb
server.port=9001
camunda.api.url=http://localhost:9000/engine-rest
```

### 📄 bpm-engine (`application.properties`)
```properties
# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://msgf.postgres.database.azure.com:5432/toyota
spring.datasource.username=${DB_USERNAME:software}
spring.datasource.password=${DB_PASSWORD:Elaguacate12}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=9000
```

### 📄 bpm-engine (`application.yaml`)
```yaml
spring.datasource.url: jdbc:h2:file:./camunda-h2-database

camunda.bpm.admin-user:
  id: demo
  password: demo
```

## 🚀 Instalación y Ejecución

### 📋 Requisitos Previos
- ☕ **Java 21+** (OpenJDK recomendado)
- 🔨 **Maven 3.8+**
- 🐘 **PostgreSQL 15+** (para producción)
- 🌐 **Puerto 9000 y 9001** disponibles

### 🛠️ Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone <https://github.com/nicolaslozanoc12/prog.Soft.Proyecto/>
cd prog.Soft.Proyecto-main
```

2. **Configurar Base de Datos** (Opcional - usa H2 por defecto)
```sql
CREATE DATABASE toyota;
CREATE USER software WITH PASSWORD 'Elaguacate12';
GRANT ALL PRIVILEGES ON DATABASE toyota TO software;
```

3. **Ejecutar bmp-engine**
```bash
cd bpm-engine
./mvnw spring-boot:run
```

4. **Ejecutar AppWeb** (en otra terminal)
```bash
cd AppWeb
./mvnw spring-boot:run
```

5. **Verificar instalación**
- 🌐 Frontend: http://localhost:9001
- 🔄 Camunda Cockpit: http://localhost:9000/camunda
- 📡 API REST: http://localhost:9000/engine-rest

## 📚 Uso del Sistema

### 👤 Roles de Usuario
- **Operador de Línea**: Reporta hallazgos y anomalías
- **Supervisor**: Gestiona escalamientos y toma decisiones
- **Personal de Bodega**: Maneja reposición de insumos
- **Administrador**: Configura procesos y monitorea sistema

### 🔄 Flujos de Trabajo Principales

1. **Reporte de Anomalía**
   - Operador detecta problema → Registra en sistema → Proceso automático de evaluación

2. **Gestión de Inventario**
   - Sistema detecta bajo stock → Genera solicitud automática → Aprobación supervisor

3. **Control de Calidad**
   - Inspección programada → Detección de problemas → Escalamiento automático

## 🧪 Testing

```bash
# Ejecutar tests en AppWeb
cd AppWeb
./mvnw test

# Ejecutar tests en bpm-engine
cd bmp-engine
./mvnw test
```

## 📈 Monitoreo y Métricas

### 🔍 Endpoints de Salud
- **AppWeb Health**: http://localhost:9001/actuator/health
- **bpm-engine Health**: http://localhost:9000/actuator/health

### 📊 Métricas Camunda
- **Cockpit Dashboard**: Monitoreo de procesos en tiempo real
- **Admin Console**: Gestión de usuarios y configuraciones
- **Tasklist**: Lista de tareas pendientes por usuario

## 📝 Documentación Adicional

- 📖 [Documentación Camunda](https://docs.camunda.org/)
- 🌱 [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## 👥 Equipo de Desarrollo

- **Equipo**: Software1
- **Proyecto**: Sistema Toyota - Control de Calidad
- **Versión**: 1.0.0-SNAPSHOT
