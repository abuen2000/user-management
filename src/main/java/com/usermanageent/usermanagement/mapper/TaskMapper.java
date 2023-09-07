package com.usermanageent.usermanagement.mapper;


import com.usermanageent.usermanagement.dto.TaskDTO;
import com.usermanageent.usermanagement.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface TaskMapper {

    TaskDTO asTaskDTO(Task task);

    Task asTask(TaskDTO taskDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    void updateTaskFromDTO(TaskDTO taskDTO, @MappingTarget Task task);

}
