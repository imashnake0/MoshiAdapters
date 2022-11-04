package com.imashnake.moshiadapter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.imashnake.moshiadapter.ui.theme.MoshiAdapterTheme
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoshiAdapterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                        .addLast(Gender::class.java, EnumJsonAdapter.create(Gender::class.java).withUnknownFallback(Gender.UNKNOWN))
                        .build()

                    val jsonAdapter = moshi.adapter(Person::class.java)

                    val person = jsonAdapter.fromJson(json)

                    Text(person.toString())
                }
            }
        }
    }
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
