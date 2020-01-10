package com.jd.pokerSessions.converters;

import com.jd.pokerSessions.commands.HandCommand;
import com.jd.pokerSessions.model.Hand;
import com.jd.pokerSessions.model.Session;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class HandCommandToHand implements Converter<HandCommand, Hand> {

    @Nullable
    @Override
    public Hand convert(HandCommand handCommand) {
        if (handCommand == null) {
            return null;
        }

        Hand hand = new Hand();

        hand.setHandDate(handCommand.getHandDate());
        hand.setHandTime(handCommand.getHandTime());
        hand.setId(handCommand.getId());
        hand.setHandId(handCommand.getHandId());
        hand.setPosition(handCommand.getPosition());
        hand.setSawFlop(handCommand.isSawFlop());
        hand.setSawTurn(handCommand.isSawTurn());
        hand.setSawRiver(handCommand.isSawRiver());
        hand.setSawShowdown(handCommand.isSawShowdown());
        hand.setH1(handCommand.getH1());
        hand.setH2(handCommand.getH2());
        hand.setB1(handCommand.getB1());
        hand.setB2(handCommand.getB2());
        hand.setB3(handCommand.getB3());
        hand.setB4(handCommand.getB4());
        hand.setB5(handCommand.getB5());

        if(handCommand.getSessionId() != null){
            Session session = new Session();
            session.setId(handCommand.getSessionId());
            hand.setSession(session);
            session.addHand(hand);
        }

        return hand;
    }
}
