package ru.umom.smolaton.shared.errors.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.umom.smolaton.shared.errors.common.UnknownError
import ru.umom.smolaton.shared.utils.genereateErrorResponse

@RestControllerAdvice
class UnknownError {

    @ExceptionHandler(UnknownError::class)
    fun onNotFound(error: UnknownError): ResponseEntity<*>{
        return genereateErrorResponse(error.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}