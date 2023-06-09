package Step1_Delete_Pool;

import java.util.Collection;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.Process;
public class MergeProcess {

    public static void merge(BpmnModelInstance modelInstance) {
        // Create a new process element to hold the merged elements
        Process mergedProcess = modelInstance.newInstance(Process.class);
        mergedProcess.setId("mergedProcess");
        mergedProcess.setName("Merged Process");

        // Add the merged process to the model instance
        modelInstance.getDefinitions().addChildElement(mergedProcess);

        // Iterate over all process elements in the model instance
        modelInstance.getModelElementsByType(Process.class).forEach(process -> {
            // Iterate over all child elements of the process element
            process.getChildElementsByType(BaseElement.class).forEach(child -> {
                // Add the child element to the merged process
                mergedProcess.addChildElement(child);
            });

            // Remove the process element from the model instance

            Collection<Process> processes = modelInstance.getModelElementsByType(Process.class);
            for (Process process_item : processes) {
                if (process_item.getChildElementsByType(BaseElement.class).isEmpty()) {
                    modelInstance.getDefinitions().removeChildElement(process_item);
                }
            }
        });
    }
}
