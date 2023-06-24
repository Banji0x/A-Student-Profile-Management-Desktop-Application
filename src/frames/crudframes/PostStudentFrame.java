package frames.crudframes;

import java.awt.*;

import static enums.OperationEnums.POST;

public class PostStudentFrame extends AbstractStudentOperationsFrame {
    public PostStudentFrame() throws HeadlessException {
        super("Add Student Details", POST);
    }
}
