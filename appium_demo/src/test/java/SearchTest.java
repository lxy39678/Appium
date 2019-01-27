import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.MainPage;
import page.SearchPage;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest {

    static MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void beforeAll(){
        mainPage = MainPage.start();
        searchPage = mainPage.gotoSearch();
    }

    @ParameterizedTest
    @CsvSource({
            "pdd,拼多多",
            "alibaba,阿里巴巴",
            "sogo,搜狗"
    })
    void searchTest(String keywork,String name){
        String content = searchPage.search(keywork).getAll().get(0);
        assertThat(content,equalTo(name));
    }

    @Test
    void select(){
        ArrayList<String> array = searchPage.search("mi").addSelected();
        assertThat(array,hasItems("followed_btn","follow_btn"));
    }
}
