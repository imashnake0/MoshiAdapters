package com.imashnake.moshiadapter.parse

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun parseEnum(): Person {
    // Intentional typo!
    val json = """
        {
            "name":"imashnake0",
            "age":20,
            "gender": "MLE"
        }
    """.trimIndent()

    // Reflection
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .addLast(
            Gender::class.java,
            EnumJsonAdapter.create(Gender::class.java).withUnknownFallback(Gender.UNKNOWN)
        )
        .build()

    val jsonAdapter = moshi.adapter(Person::class.java)

    return jsonAdapter.fromJson(json) ?: Person(
        name = "null :dying_weary",
        age = 0,
        gender = Gender.UNKNOWN
    )
}

data class Person(
    val name: String,
    val age: Int = 80,
    val gender: Gender
)

enum class Gender {
    MALE,
    FEMALE,
    UNKNOWN
}
