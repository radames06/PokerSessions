package com.jd.pokerSessions.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class HandConverter implements AttributeConverter<Card, String> {
    @Override
    public String convertToDatabaseColumn(Card card) {
        if (card == null) {
            return null;
        } else {
            String valueString;
            Integer value = card.getValue();
            switch (value) {
                case 14:
                    valueString = "A";
                    break;
                case 13:
                    valueString = "K";
                    break;
                case 12:
                    valueString = "Q";
                    break;
                case 11:
                    valueString = "J";
                    break;
                case 10:
                    valueString = "T";
                    break;
                default:
                    valueString = value.toString();
            }

            return valueString + card.getColor();
        }
    }

    @Override
    public Card convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        } else {
            return Card.valueOf("_" + s);
        }
    }
}
