package com.usermanageent.usermanagement.mapper;


import com.usermanageent.usermanagement.dto.TaskDTO;
import com.usermanageent.usermanagement.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface TaskMapper {

    TaskDTO asTaskDTO(Task task);
    Task asTask(TaskDTO taskDTO);
}
