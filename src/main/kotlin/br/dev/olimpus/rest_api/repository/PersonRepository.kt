package br.dev.olimpus.rest_api.repository

import br.dev.olimpus.rest_api.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long?>