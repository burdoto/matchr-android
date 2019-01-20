package de.kaleidox.matchr.android.listener;

import de.kaleidox.matchr.android.event.Event;

public interface GenericListener<E extends Event> {
    void onEvent(E event);
}
