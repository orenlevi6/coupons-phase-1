package dao;

import beans.Company;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password) throws SQLException;

    boolean addCompany(Company company);

    boolean updateCompany(Company company);

    boolean deleteCompany(int companyID);

    List<Company> getAllCompanies(String sql, Map<Integer, Object> value);

    Company getOneCompany(int companyID);

}