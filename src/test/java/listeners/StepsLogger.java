package listeners;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;
/**
 * Created by Любовь on 18.12.2017.
 */
@SuppressWarnings("JavadocType")
@Slf4j
public class StepsLogger implements StepLifecycleListener {

    @Override
    public void beforeStepStop(final StepResult result) {
        //log.info("Finishing step: {}", result.getName());
    }
}
