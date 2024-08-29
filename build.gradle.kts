import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.7"
	id("io.spring.dependency-management") version "1.1.0"
	id("jacoco")
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("kapt") version "1.4.10"
}

group = "com.example.tutorial"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

object Versions {
	const val logback = "7.2"
	const val springMockK = "3.0.0"
	const val springCloud = "2021.0.3"
	const val springCloudGcpVersion ="2.0.0"
	const val r2dbc = "Arabba-SR10"
	const val springR2dbc = "1.4.0"
	const val bouncyCastle = "1.70"
	const val apacheCommons = "1.21"
	const val securityCore = "5.7.5"
	const val springCore = "5.3.23"
	const val glassfish = "3.0.4"
	const val jackson = "2.14.0-rc2"
	const val log4j = "2.19.0"
	const val log4jToSlf4j = "2.19.0"
	const val junitVersion = "4.13"
	const val nettyAll = "4.1.84.Final"
	const val stdlib = "1.7.10"
	const val postgresql = "42.4.2"
	const val actuator = "2.7.5"
	const val jacksonModuleKotlin = "2.14.0-rc2"
	const val springCloudStream = "3.2.3"
	const val springBootStarterSecurity = "2.7.5"
}

dependencyManagement {
	imports {
		mavenBom("io.r2dbc:r2dbc-bom:${Versions.r2dbc}")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
	implementation("io.projectreactor.netty:reactor-netty-core:1.0.24")
	implementation("org.scala-lang:scala-library:2.13.10")
	implementation("org.scala-lang:scala-reflect:2.13.10")
	implementation("org.webjars:swagger-ui:4.15.0")
	implementation("org.bouncycastle:bcprov-jdk15on:${Versions.bouncyCastle}")
	implementation("org.bouncycastle:bcprov-ext-jdk15on:${Versions.bouncyCastle}")
	implementation("org.springframework.boot:spring-boot-starter-actuator:${Versions.actuator}")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security:${Versions.springBootStarterSecurity}")
	implementation("org.springframework.security:spring-security-core:${Versions.securityCore}")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jacksonModuleKotlin}")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")
	implementation("com.fasterxml.jackson.module:jackson-module-scala_3:2.14.0-rc2")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.apache.logging.log4j:log4j-api:${Versions.log4j}")
	implementation("org.apache.logging.log4j:log4j-to-slf4j:${Versions.log4jToSlf4j}")
	implementation("net.logstash.logback:logstash-logback-encoder:${Versions.logback}")
	implementation("org.apache.commons:commons-pool2:2.11.1")
	implementation("io.netty:netty-all:${Versions.nettyAll}")
	implementation("io.projectreactor.netty:reactor-netty:1.0.24")
	implementation("org.springframework.retry:spring-retry:1.3.4")
	implementation("org.springframework.security:spring-security-crypto:${Versions.securityCore}")
	implementation("org.springframework.security:spring-security-rsa:1.0.11.RELEASE")
	implementation("org.springframework.vault:spring-vault-core:2.3.2")
	implementation("io.zipkin.zipkin2:zipkin:2.23.19")
	implementation("com.jayway.jsonpath:json-path")
	implementation("org.springframework.data:spring-data-r2dbc")
	implementation("io.r2dbc:r2dbc-pool")
	implementation("io.r2dbc:r2dbc-postgresql")
	implementation("org.postgresql:postgresql:${Versions.postgresql}")
	implementation("org.apache.logging.log4j:log4j-api:${Versions.log4j}")
	implementation("org.apache.logging.log4j:log4j-to-slf4j:2.19.0")
	implementation("org.springframework.cloud:spring-cloud-stream:${Versions.springCloudStream}")
	implementation("org.apache.commons:commons-lang3:3.12.0")
	implementation("org.apache.commons:commons-compress:${Versions.apacheCommons}")
	implementation("org.glassfish:jakarta.el:${Versions.glassfish}")
	implementation("io.github.classgraph:classgraph:4.8.149")
	implementation("org.springframework.cloud:spring-cloud-function-core:3.2.7")
	implementation("org.springframework.cloud:spring-cloud-function-context:3.2.7")

	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("com.ninja-squad:springmockk:${Versions.springMockK}")
	testImplementation("junit:junit:${Versions.junitVersion}")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable", "-Xinline-classes")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("bootRunDev") {
	group = "application"
	description = "Runs this project as a Spring Boot application with the dev profile"
	doFirst {
		tasks.bootRun.configure {
			systemProperty("spring.profiles.active", "dev")
		}
	}
	finalizedBy("bootRun")
}
