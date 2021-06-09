package at.team30.setroute

import android.widget.EditText
import at.team30.setroute.Helper.EmailHelper
import com.dumbster.smtp.SimpleSmtpServer
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException

@RunWith(JUnit4::class)
class EmailTest {

    lateinit var emailHelper : EmailHelper
    lateinit var smtpServer : SimpleSmtpServer
    var port = 666

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



    @Test
    fun createEmailHelperWithoutAuthentication() {
        EmailHelper(
            authenticate = false,
            userName = "Max Musterman",
            password = null
        )
    }

    @Before
    fun setUpTest() {
        smtpServer = SimpleSmtpServer(port)
    }

    @Test
    @Ignore
    fun emailReceived() {
        var text = "Very best feedback."
        var receiver = "some@anything.org"
        var subject = "Very important issue"

        emailHelper = EmailHelper(
            smtpHost = "localhost",
            smtpPort = port,
            enableStartTtls = false,
            userName = null,
            password = null,
            sender = "bestemail@provider.com",
            authenticate = false
        )

        runBlocking {
            emailHelper.sendEmail(text, receiver, subject)
        }
        Assert.assertEquals(1, smtpServer.receivedEmailSize)
    }

}