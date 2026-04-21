package view;

import dto.ResponseDTO;
import java.util.ArrayList;
import java.util.List;

public class TaskView {

    private List<ResponseDTO> responseDTO;

    public TaskView() {
        responseDTO = new ArrayList<>();
    }

    // Phương thức để Controller đẩy dữ liệu vào View
    public void setResponseDTO(List<ResponseDTO> responseDTO) {
        this.responseDTO = responseDTO;
    }

    public void printTask() {
        //Cài đặt Header cho bảng //
        System.out.println(String.format("%-5s%-15s%-15s%-15s%-15s%-15s%-15s",
                "ID", "Name", "Task Type", "Date", "Time", "Assign", "Reviewer"));

        // Duyệt qua danh sách ResponseDTO để hiển thị nội dung Body //
        for (ResponseDTO res : responseDTO) {
            System.out.println(res.toString());
        }
    }
}