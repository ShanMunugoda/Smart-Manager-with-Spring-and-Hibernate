/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.comp.business.custom.Impl;

import lk.ijse.comp.business.custom.EmployeeBO;
import lk.ijse.comp.dao.custom.BranchDAO;
import lk.ijse.comp.dao.custom.EmployeeDAO;
import lk.ijse.comp.dto.BranchDTO;
import lk.ijse.comp.dto.EmployeeDTO;
import lk.ijse.comp.entity.Branch;
import lk.ijse.comp.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDMROX
 */
@Component
@Transactional
public class EmployeeBOImpl implements EmployeeBO {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private BranchDAO branchDAOImpl;
    private SessionFactory sessionFactory;

    public EmployeeBOImpl() {
//        employeeDAO= AppInitializer.ctx.getBean(EmployeeDAOImpl.class);
//        branchDAOImpl=AppInitializer.ctx.getBean(BranchDAOImpl.class);

//        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws Exception {
        try {

            Employee emp = new Employee(dto.getEid(), dto.getEname(), dto.getEaddress(), new Branch(dto.getBid()));
            employeeDAO.Save(emp);

            return true;
        }catch (HibernateException exp){
            return false;
        }
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
//       Employee emp=new Employee(dto.getEid(), dto.getEname(),dto.getEaddress(),new Branch(dto.getBid()));
//        boolean rst = employeeDAO.Update(emp);
//        return rst;
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        try{

            employeeDAO.Delete(id);

            return true;
        }catch (HibernateException exp){
            return false;
        }

    }

    @Override
    public ArrayList<EmployeeDTO> allEmployee() throws Exception {

        try  {


            ArrayList<EmployeeDTO> arr = new ArrayList<>();
            List<Employee> allemp = employeeDAO.getAll();

            for (Employee employee : allemp) {
                EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEid(), employee.getEname(), employee.getEaddress(), employee.getBranch().getBid());
                arr.add(employeeDTO);

            }

            return arr;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public EmployeeDTO findById(String eid) throws Exception {

        try{

            Employee find = employeeDAO.findById(eid);
            EmployeeDTO employeedto = new EmployeeDTO(find.getEid(), find.getEname(), find.getEaddress(), find.getBranch().getBid());

            return employeedto;
        }catch (HibernateException exp){
            return null;
        }
    }

    @Override
    public ArrayList<BranchDTO> allBids() throws Exception {
        try  {

            List<Branch> branch = branchDAOImpl.getAll();
            ArrayList<BranchDTO> arr = new ArrayList<>();
            for (Branch branch1 : branch) {
                BranchDTO b = new BranchDTO(branch1.getBid());
                arr.add(b);

            }

            return arr;
        }catch (HibernateException exp){
            return null;
        }
    }
}
