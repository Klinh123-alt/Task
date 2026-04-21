package service;

import dto.ResponseDTO;
import dto.TaskDTO;
import java.util.List;
import repository.TaskRepository;
import utils.Validation;

public class TaskService {

    // Service chỉ làm việc với Repository (không liên quan View)
    private final TaskRepository repo = new TaskRepository();

    public int addTask(TaskDTO dto) throws Exception {
        double from = dto.getPlanFrom();
        double to = dto.getPlanTo();
        
        Validation.checkPlanTime(from,to);
        Validation.checkStep(from);
        Validation.checkStep(to);
        Validation.checkWorkingTime(from);
        Validation.checkWorkingTime(to);
        
        return repo.addTask(dto);
    }

    public boolean deleteTask(int id) {

        // Business logic: kiểm tra tồn tại trước khi xóa
        if (repo.findById(id) == null) {
            return false;
        }

        return repo.deleteTask(id);
    }

    public List<ResponseDTO> getAllTasks() throws Exception {

        // Business logic: kiểm tra danh sách rỗng
        if (repo.isEmpty()) {
            throw new Exception("List is empty");
        }

        // Trả dữ liệu về cho Controller
        return repo.getAllTasks();
    }
}