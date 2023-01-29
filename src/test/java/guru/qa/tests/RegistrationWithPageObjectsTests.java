package guru.qa.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import static io.qameta.allure.Allure.step;

public class RegistrationWithPageObjectsTests extends TestBase {
    Faker faker = new Faker();
    @Test
    @Tag("qa_guru_Jenkins")
    void successfulRegistrationTest() {
        String userName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String eMail = faker.internet().emailAddress();
        String userNumber = faker.number().digits(10);
        String currentAddress = faker.address().streetAddress();
        String userPicture = "1.jpg";

        String birthMonth = "January";
        String birthYear = "1993";
        String birthDay = "06";

        step("Открыть форму регистрации и проверить, что форма имеет правильное название", () -> {
        registrationPage.openPage();
        });

        step("Заполнить поля формы и нажать кнопку", () -> {
        registrationPage.setFirstName(userName)
                .setLastName(lastName)
                .setEmail(eMail)
                .setGender()
                .setUserNumber(userNumber)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubjects()
                .setHobbies()
                .setPictures()
                .setUserAddress(currentAddress)
                .setState()
                .setCity()
                .submit();
        });

        step("Проверить корректность заполнения данных в таблице", () -> {
        registrationPage.verifyResultModalAppears()
                .verifyResults("Student Name", userName + " " + lastName)
                .verifyResults("Student Email", eMail)
                .verifyResults("Gender", "Male")
                .verifyResults("Mobile", userNumber)
                .verifyResults("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .verifyResults("Subjects", "Math")
                .verifyResults("Hobbies", "Sports")
                .verifyResults("Picture", userPicture)
                .verifyResults("Address", currentAddress)
                .verifyResults("State and City", "NCR" + " " + "Delhi");
        });
    }
}