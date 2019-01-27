//import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.LoginPage;
import page.MainPage;
import page.ProfilePage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest {

    static MainPage mainPage;
    static ProfilePage profilePage;

    @BeforeAll
    static void beforeAll(){
        mainPage = MainPage.start();
        profilePage = mainPage.gotoProfile();
    }
//    @Test
    @ParameterizedTest
    @CsvSource({
            "15600534760,123456,用户名或密码错误",
            "1560053476,123456,手机号码填写错误"
    })

    void passwdLogin(String username,String password,String expection){
        LoginPage loginPage = profilePage.gotoLogin();
        loginPage.passwordFail(username,password);
        assertThat(loginPage.getMessage(),equalTo(expection));
        profilePage = loginPage.gotoProfile();
    }
}
