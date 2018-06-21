package com.antkorwin.smutilssample;

import com.antkorwin.smutilssample.statemachine.Events;
import com.antkorwin.smutilssample.statemachine.States;
import com.antkorwin.statemachineutils.wrapper.StateMachineWrapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 21.06.2018.
 *
 * @author Korovin Anatoliy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTest {

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired
    @Qualifier("stateMachineRollbackWrapper")
    private StateMachineWrapper<States, Events> stateMachineWrapper;

    @Test
    public void testRollbackAfterThrowsException() throws Exception {

        // Arrange
        StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
        RuntimeException expectedException = new RuntimeException("stop!");
        Exception actualException = null;

        try {
            // Act
            stateMachineWrapper.runWithRollback(stateMachine, machine -> {
                machine.sendEvent(Events.TURN_RIGHT);
                throw expectedException;
            });
        } catch (Exception e) {
            actualException = e;
        }

        // Assert
        Assertions.assertThat(actualException)
                  .isEqualTo(expectedException);

        Assertions.assertThat(stateMachine.getState().getId())
                  .isEqualTo(States.SWITCH);
    }
}
