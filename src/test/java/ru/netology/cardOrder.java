package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class cardOrder {
    @Test
    void cardOrderFieldsTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Калининград");

        SelenideElement dateElement = $("[data-test-id=date] input[class=input__control]");
        dateElement.doubleClick().sendKeys(Keys.BACK_SPACE);
        String dateMeeting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        dateElement.setValue(dateMeeting);

        $("[data-test-id=name] input").setValue("Антонов Николай");
        $("[data-test-id=phone] input").setValue("+79789919990");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();

        $("[data-test-id=notification]")
                .$(withText("Встреча успешно забронирована"))
                .waitUntil(visible, 15000);

    }
}
