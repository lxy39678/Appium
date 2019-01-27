package page;
import org.openqa.selenium.By;

public class StockDetailPage extends BasePage{


    public StockDetailPage Selected(){
        By selected = text("加自选");
        find(selected).click();
        return this;
    }

    public StockDetailPage Unselected(){
        By selected = text("设自选");
        find(selected).click();
        By unselected = text("删除自选");
        find(unselected).click();
        return this;
    }
}
