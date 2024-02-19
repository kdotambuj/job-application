package com.akay.jobapplication.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateById(Long id,Job updatedJob);
}
