package utils;

import dto.Contact;
import net.datafaker.Faker;

public class ContactFactory {
    static Faker faker = new Faker();

    public static Contact positiveContact() {
        return Contact.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.number().digits(10))
                .address(faker.address().fullAddress())
                .description(faker.redDeadRedemption2().quote())
                .build();
    }
}