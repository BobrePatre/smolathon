package ru.umom.smolaton.shared.errors.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.umom.smolaton.shared.errors.common.NotFoundError
import ru.umom.smolaton.shared.utils.genereateErrorResponse

@RestControllerAdvice
class NotFoundErrorController {

    @ExceptionHandler(NotFoundError::class)
    fun onNotFound(error: NotFoundError): ResponseEntity<*>{
        return genereateErrorResponse(error.message, HttpStatus.NOT_FOUND)
    }

}