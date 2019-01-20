package de.kaleidox.matchr.android.tests


import de.kaleidox.matchr.android.listener.ListenerManager

import org.junit.Test

import static org.easymock.EasyMock.expect
import static org.easymock.EasyMock.mock
import static org.easymock.EasyMock.replay

class ListenerManagerTest {
    @Test
    void testDetachNow() {
        def mock = mock(ListenerManager)

        expect(mock.detachNow()).andReturn(true)

        replay(mock)
    }
}
