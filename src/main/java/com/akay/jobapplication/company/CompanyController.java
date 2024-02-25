package com.akay.jobapplication.company;

import jakarta.persistence.OneToMany;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;




    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if (company!=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
         companyService.addCompany(company);
         return new ResponseEntity<>("Company added",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateCompany(@PathVariable Long id,@RequestBody Company updatedCompany){
        boolean updated = companyService.updateCompany(id,updatedCompany);
        if (updated){
            return new ResponseEntity<>("Company updated",HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        boolean deleted = companyService.deleteById(id);
        if (deleted){
            return new ResponseEntity<>("Job Deleted",HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
