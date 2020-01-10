package com.jd.pokerSessions.converters;

import com.jd.pokerSessions.commands.SessionCommand;
import com.jd.pokerSessions.model.Session;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SessionCommandToSession implements Converter<SessionCommand, Session> {

    private final HandCommandToHand handConverter;

    public SessionCommandToSession(HandCommandToHand handConverter) {
        this.handConverter = handConverter;
    }

    @Nullable
    @Override
    public Session convert(SessionCommand sessionCommand) {
        if (sessionCommand == null) {
            return null;
        }

        final Session session = new Session();

        session.setDate(sessionCommand.getDate());
        session.setId(sessionCommand.getId());
        session.setOwner(sessionCommand.getOwner());
        session.setRoom(sessionCommand.getRoom());

        if (sessionCommand.getSessionHands() != null && sessionCommand.getSessionHands().size() > 0){
            sessionCommand.getSessionHands()
                    .forEach(hh -> session.getSessionHands().add(handConverter.convert(hh)));
        }

        return session;
    }
}
