package br.dev.olimpus.rest_api.conteiner

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.lifecycle.Startables
import java.util.stream.Stream

@ContextConfiguration(initializers = [AbstractIntegrationTest.Initializer::class])
open class AbstractIntegrationTest {

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            startContainers()
            val env = applicationContext.environment
            val testcontainers = MapPropertySource("testcontainers", createConnectionConfiguratio())

            env.propertySources.addFirst(testcontainers)
        }

        companion object {

            private val mysql: MySQLContainer<*> = MySQLContainer("mysql:8.0.28")

            private fun createConnectionConfiguratio(): MutableMap<String, Any> {
                return java.util.Map.of(
                    "spring.datasource.url", mysql.jdbcUrl,
                    "spring.datasource.username", mysql.username,
                    "spring.datasource.password", mysql.password
                )
            }

            private fun startContainers() {
                Startables.deepStart(Stream.of(mysql)).join()
            }
        }
    }
}