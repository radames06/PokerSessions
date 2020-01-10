package com.jd.pokerSessions.converters;

import com.jd.pokerSessions.commands.HandCommand;
import com.jd.pokerSessions.model.Hand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class HandToHandCommand implements Converter<Hand, HandCommand> {

    @Nullable
    @Override
    public HandCommand convert(Hand hand) {
        if (hand == null) {
            return null;
        }

        HandCommand command = new HandCommand();

        command.setHandDate(hand.getHandDate());
        command.setHandTime(hand.getHandTime());
        command.setId(hand.getId());
        command.setHandId(hand.getHandId());
        command.setPosition(hand.getPosition());
        command.setSawFlop(hand.isSawFlop());
        command.setSawTurn(hand.isSawTurn());
        command.setSawRiver(hand.isSawRiver());
        command.setSawShowdown(hand.isSawShowdown());
        command.setH1(hand.getH1());
        command.setH2(hand.getH2());
        command.setB1(hand.getB1());
        command.setB2(hand.getB2());
        command.setB3(hand.getB3());
        command.setB4(hand.getB4());
        command.setB5(hand.getB5());

        if (hand.getSession()!=null) {
            command.setSessionId(hand.getSession().getId());
        }

        return command;
    }
}
