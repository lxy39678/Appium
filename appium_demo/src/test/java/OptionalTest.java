import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.MainPage;
import page.SearchPage;
import page.StockDetailPage;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class OptionalTest {

    static MainPage mainPage;
    static SearchPage searchPage;
    static StockDetailPage stockDetailPage;

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
    void searchTestSelect(String keyword,String name){
        String content = searchPage.search(keyword).getAll().get(0);
        assertThat(content,equalTo(name));
        stockDetailPage = mainPage.gotoDetail(name);
        stockDetailPage.Selected();
        stockDetailPage.Unselected();
    }

}
