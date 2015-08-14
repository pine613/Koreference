package jp.takuji31.koreference.gson.test

import android.content.Context
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.AndroidTestCase
import jp.takuji31.koreference.gson
import org.junit
import org.junit.runner.RunWith
import kotlin.properties.Delegates
import kotlin.test.*

/**
 * Created by takuji on 2015/08/14.
 */
RunWith(AndroidJUnit4::class)
public class GsonModelTest {
    val context: Context by Delegates.lazy {
        InstrumentationRegistry.getTargetContext()
    }

    val pref : SharedPreferences by Delegates.lazy {
        context.getSharedPreferences("test", Context.MODE_PRIVATE)
    }

    junit.Before
    fun setup() {
        pref.edit().clear().apply()
    }

    junit.After
    fun teardown() {
    }

    junit.Test
    fun testGsonModel() {
        val model = GsonTestModel(pref = pref)

        assertEquals(model.person, Person("Taro", "Yamada"), "Person default value")
        assertNull(model.nullablePerson, "Nullable person default value")

        model.person = Person("Hanako", "Suzuki")
        model.nullablePerson = Person("Jiro", "Sato")

        assertEquals(model.person, Person("Hanako", "Suzuki"), "Person new value")
        assertEquals(model.nullablePerson, Person("Jiro", "Sato"), "Nullable person new value")
    }
}