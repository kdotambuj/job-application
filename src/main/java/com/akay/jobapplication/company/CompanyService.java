package com.akay.jobapplication.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void addCompany(Company company);

    boolean updateCompany(Long id,Company updatedCompany);

    boolean deleteById(Long id);

}
