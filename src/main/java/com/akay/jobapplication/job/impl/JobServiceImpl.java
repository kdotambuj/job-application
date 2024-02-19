package com.akay.jobapplication.job.impl;

import com.akay.jobapplication.job.Job;
import com.akay.jobapplication.job.JobRepository;
import com.akay.jobapplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {

         jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
           jobRepository.deleteById(id);
           return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, Job updatedJob) {

        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()){
            Job job = optionalJob.get();

            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());

            jobRepository.save(job);

            return true;
        }

        return false;
    }
}
