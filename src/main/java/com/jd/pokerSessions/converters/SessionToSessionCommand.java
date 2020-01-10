package com.jd.pokerSessions.converters;

import com.jd.pokerSessions.commands.SessionCommand;
import com.jd.pokerSessions.model.Session;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SessionToSessionCommand implements Converter<Session, SessionCommand> {

    private final HandToHandCommand handConverter;

    public SessionToSessionCommand(HandToHandCommand handConverter) {
        this.handConverter = handConverter;
    }

    @Nullable
    @Override
    public SessionCommand convert(Session session) {
        if (session == null) {
            return null;
        }

        final SessionCommand command = new SessionCommand();

        command.setDate(session.getDate());
        command.setId(session.getId());
        command.setOwner(session.getOwner());
        command.setRoom(session.getRoom());

        if (session.getSessionHands() != null && session.getSessionHands().size() > 0){
            session.getSessionHands()
                    .forEach(hh -> command.getSessionHands().add(handConverter.convert(hh)));
        }

        return command;
    }
}
