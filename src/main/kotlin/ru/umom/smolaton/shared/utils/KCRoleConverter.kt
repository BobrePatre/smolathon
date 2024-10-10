package ru.umom.smolaton.shared.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

// класс конвертер из данных JWT в роли spring security
@Component
class KCRoleConverter(
    @Value("\${keycloak.clients.core.client-id}") private val coreClientId: String,
) : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val returnValue = mutableListOf<GrantedAuthority>()
        val resourceAccess = jwt.claims["resource_access"] as Map<*, *>?
        val clientAccess = resourceAccess?.get(coreClientId) as Map<*, *>?

        clientAccess?.let {
            val roles = it["roles"] as List<*>?
            roles?.forEach { role ->
                returnValue.add(SimpleGrantedAuthority("ROLE_$role"))
            }
        }

        return returnValue
    }
}


//@Component
//class KCRoleConverter(
//    @Value("\${keycloak.client.core}") private val coreClientId: String,
//) : Converter<Jwt, Collection<GrantedAuthority>> {
//    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
//
//        val returnValue: MutableCollection<GrantedAuthority> = ArrayList()
//        val clients = listOf(coreClientId)
//
//        clients.forEach { clientId ->
//            ((jwt.claims["resource_access"] as Map<*, *>?)?.get(clientId) as Map<*, *>?).let { clientAccess ->
//                clientAccess?.let {
//                    (it["roles"] as List<*>?)?.let { roles ->
//                        if (roles.isNotEmpty()) {
//                            roles.forEach { role ->
//                                run {
//                                    returnValue.add(SimpleGrantedAuthority("ROLE_$role"))
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return returnValue
//    }
//}