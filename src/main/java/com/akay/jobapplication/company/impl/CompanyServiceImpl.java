package com.akay.jobapplication.company.impl;

import com.akay.jobapplication.company.Company;
import com.akay.jobapplication.company.CompanyRepository;
import com.akay.jobapplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id,Company updatedCompany) {
        Optional<Company> optionalCompany =  companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();

            company.setTitle(updatedCompany.getTitle());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());

            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else return false;

    }
}
