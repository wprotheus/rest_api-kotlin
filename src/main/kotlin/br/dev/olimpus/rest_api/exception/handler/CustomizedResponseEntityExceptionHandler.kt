package br.dev.olimpus.rest_api.exception.handler

import br.dev.olimpus.rest_api.exception.ExceptionResponse
import br.dev.olimpus.rest_api.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse?> {
        val exceptionResponse =
            ExceptionResponse(
                Date(),
                ex.message,
                request.getDescription(false)
            )
        return ResponseEntity<ExceptionResponse?>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleBadRequestExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse?> {
        val exceptionResponse =
            ExceptionResponse(
                Date(),
                ex.message,
                request.getDescription(false)
            )
        return ResponseEntity<ExceptionResponse?>(exceptionResponse, HttpStatus.NOT_FOUND)
    }
}