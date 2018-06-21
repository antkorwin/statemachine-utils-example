package com.antkorwin.smutilssample;

import com.antkorwin.smutilssample.statemachine.Events;
import com.antkorwin.smutilssample.statemachine.States;
import com.antkorwin.statemachineutils.resolver.StateMachineResolver;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created on 21.06.2018.
 *
 * @author Korovin Anatoliy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResolverTests {

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired
    private StateMachineResolver<States, Events> stateMachineResolver;

    private StateMachine<States, Events> stateMachine;

    @Before
    public void setUp() throws Exception {
        stateMachine = stateMachineFactory.getStateMachine();
    }

    @Test
    public void testResolverWithoutGuard() {
        // Arrange
        // Act
        List<Events> availableEvents = stateMachineResolver.getAvailableEvents(stateMachine);
        // Asserts
        Assertions.assertThat(availableEvents)
                  .containsOnly(Events.TURN_LEFT,
                                Events.TURN_RIGHT,
                                Events.GO_AHEAD);
    }

}
