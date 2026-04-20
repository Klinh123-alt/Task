package controller;

import constant.Message;
import dto.ResponseDTO;
import dto.TaskDTO;
import java.util.List;
import service.TaskService;
import view.TaskView;

public class Controller {

    // Controller chỉ làm việc với Service (không gọi Repository nữa)
    private final TaskService service;

    // DTO chứa dữ liệu nhập từ user
    private TaskDTO dto;

    // View để hiển thị
    private final TaskView view;

    public Controller() {
        this.service = new TaskService(); // khởi tạo service
        this.dto = new TaskDTO();
        this.view = new TaskView();
    }

    public TaskView getView() {
        return view;
    }

    public TaskDTO getDto() {
        return dto;
    }

    public void addTask() throws Exception {

        // Gọi service thay vì repository
        int id = service.addTask(dto);

        // Xử lý hiển thị (Controller chịu trách nhiệm UI logic)
        if (id > 0) {
            System.out.println(Message.SUCCESS_ADD_MESSAGE);
        } else {
            System.out.println(Message.ERROR_CANNOT_ADD);
        }
    }

    public void deleteTask() {

        // Gọi service xử lý logic
        if (!service.deleteTask(dto.getId())) {
            System.out.println(Message.ERROR_CANNOT_FOUND);
        } else {
            System.out.println(Message.SUCCESS_DELETE_MESSAGE);
        }
    }

    public void showTasks() {
        try {

            // Lấy dữ liệu từ service
            List<ResponseDTO> list = service.getAllTasks();

            // Đẩy sang view để hiển thị
            view.setResponseDTO(list);
            view.printTask();

        } catch (Exception e) {

            // Hiển thị lỗi ra màn hình
            System.out.println(e.getMessage());
        }
    }

    public void setDto(TaskDTO dto) {
        this.dto = dto;
    }
}