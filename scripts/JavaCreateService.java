import org.moqui.context.ExecutionContext;
import org.moqui.entity.EntityValue;

public class TrainingService
{
    public static void createTraining(ExecutionContext ec)
    {
        // Log message
        System.out.println("File tracked");

        // Input parameters
        String trainingName = (String) ec.getContext().get("trainingName");
        String trainingDate = (String) ec.getContext().get("trainingDate");
        BigDecimal trainingPrice = (BigDecimal) ec.getContext().get("trainingPrice");
        Integer trainingDuration = (Integer) ec.getContext().get("trainingDuration");

        // Validate required fields
        if (trainingName == null || trainingName.isEmpty()) {
            ec.getMessage().addError("Training name is required.");
            return;
        }

        if (trainingDate == null || trainingDate.isEmpty()) {
            ec.getMessage().addError("Training date is required.");
            return;
        }

        // Explicitly generate a unique ID
        String trainingId = ec.getEntity().sequencedIdPrimary("MoquiTraining", null, null);

        // Create the MoquiTraining entity record
        EntityValue trainingRecord = ec.getEntity().makeValue("moqui.training.MoquiTraining");

        trainingRecord.set("trainingId", trainingId); // Explicitly set trainingId
        trainingRecord.set("trainingName", trainingName);
        trainingRecord.set("trainingDate", trainingDate);

        if (trainingPrice != null) {
            trainingRecord.set("trainingPrice", trainingPrice);
        }

        if (trainingDuration != null) {
            trainingRecord.set("trainingDuration", trainingDuration);
        }

        // Save the record
        trainingRecord = trainingRecord.create();

        // Set the output parameter
        ec.getContext().put("trainingId", trainingRecord.get("trainingId"));
    }
}
