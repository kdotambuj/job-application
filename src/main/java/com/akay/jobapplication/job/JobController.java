package com.akay.jobapplication.job;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {

        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>>findAll(){

        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job!=null){
            return new ResponseEntity<>(job,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
          boolean deleteJob = jobService.deleteJobById(id);
          if (!deleteJob){
              return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>("Job Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateById(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated = jobService.updateById(id,updatedJob);
        if (updated){
            return new ResponseEntity<>("Job Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }

}
