package com.phonepe.demo.repository.mongo;

import com.phonepe.demo.entity.mongo.Issue;
import com.phonepe.demo.model.IssueStatus;
import com.phonepe.demo.model.IssueType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String> {

    @Query("{'type': { '$in': ?0 }}")
    List<Issue> findAllByIssueTypeIn(List<IssueType> issueTypes);

    @Query("{'type': { '$in': ?0 }, 'issueStatus': $1}")
    List<Issue> findAllByIssueTypeInAndIssueStatus(List<IssueType> issueTypes, String issueStatus);

    @Query("{ 'customerServiceAgentId' : ?0 }")
    Issue findByAgentId(String agentId);

}
