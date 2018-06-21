package com.antkorwin.smutilssample.config;

import com.antkorwin.smutilssample.statemachine.Events;
import com.antkorwin.smutilssample.statemachine.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

/**
 * Created on 05.05.2018.
 *
 * @author Korovin Anatoliy
 */
@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
              .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
              .initial(States.SWITCH)
              .state(States.LEFT)
              .state(States.RIGHT)
              .state(States.BACKWARD)
              .end(States.FORWARD);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
                   .source(States.SWITCH)
                   .target(States.RIGHT)
                   .event(Events.TURN_RIGHT)
                   .and()
                   .withExternal()
                   .source(States.SWITCH)
                   .target(States.LEFT)
                   .event(Events.TURN_LEFT)
                   .and()
                   .withExternal()
                   .source(States.SWITCH)
                   .target(States.FORWARD)
                   .event(Events.GO_AHEAD)
                   .and()
                   .withExternal()
                   .source(States.SWITCH)
                   .target(States.BACKWARD)
                   .guard(wrongWay())
                   .event(Events.GO_BACK);
    }

    private Guard<States, Events> wrongWay() {
        return context -> false;
    }
}
