package com.phonepe.demo.mapper;

import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.model.dto.IssueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueMapper {

    @Mapping(source = "id", target = "uuid")
    IssueDTO map(Issue issue);

    @Mapping(source = "uuid", target = "id")
    Issue map(IssueDTO issueDTO);
}
