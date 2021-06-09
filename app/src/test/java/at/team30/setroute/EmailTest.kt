package at.team30.setroute

import at.team30.setroute.Helper.EmailHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException

@RunWith(JUnit4::class)
class EmailTest {

    @Test(expected = IllegalArgumentException::class)
    fun initIllegalArgumentsNoUsername() {
        EmailHelper(
            authenticate = true,
            userName = null
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun initIllegalArgumentsNoPassword() {
        EmailHelper(
                authenticate = true,
                userName = "Max Musterman",
                password = null
        )
    }
}