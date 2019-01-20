package de.kaleidox.matchr.android.android.listener;

import de.kaleidox.matchr.android.android.event.Event;

public interface GenericListener<E extends Event> {
    void onEvent(E event);
}
