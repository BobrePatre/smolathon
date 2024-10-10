package ru.umom.smolaton.shared.errors.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.umom.smolaton.shared.errors.common.PermissionError

@RestControllerAdvice
class PermissionErrorController {

    @ExceptionHandler(PermissionError::class)
    fun onPermissionError(error: PermissionError): ResponseEntity<*> = ResponseEntity(error.message, HttpStatus.FORBIDDEN)
}