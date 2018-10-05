/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.comp.business.custom.Impl;

import lk.ijse.comp.business.custom.BranchBO;
import lk.ijse.comp.dao.custom.BranchDAO;
import lk.ijse.comp.dto.BranchDTO;
import lk.ijse.comp.entity.Branch;
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
public class BranchBOImpl implements BranchBO {

    @Autowired
    private BranchDAO branchDAO;

    public BranchBOImpl() {
//        branchDAO= AppInitializer.ctx.getBean(BranchDAOImpl.class);

//        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    

    @Override
    public boolean saveBranch(BranchDTO dto) throws Exception {
        Branch brnh=new Branch(dto.getBid(), dto.getBname(), dto.getBaddress());
        try{

            branchDAO.Save(brnh);

        }catch (HibernateException exp) {
            return false;
        }
       return true;
        
    }

    @Override
    public boolean updateBranch(BranchDTO dto) throws Exception {
        try{

            Branch brnh = new Branch(dto.getBid(), dto.getBname(), dto.getBaddress());
            branchDAO.Update(brnh);

        }catch (HibernateException exp) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        try {


            branchDAO.Delete(id);


        }catch (HibernateException exp) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<BranchDTO> allBranch() throws Exception {
        try{

            ArrayList<BranchDTO> arr = new ArrayList<>();
            List<Branch> allbranch = branchDAO.getAll();

            for (Branch branch : allbranch) {

                BranchDTO brnh = new BranchDTO(branch.getBid(), branch.getBname(), branch.getBaddress());
                arr.add(brnh);

            }

            return arr;
        }catch (HibernateException exp) {
            exp.printStackTrace();
            return null;
        }
    }

    @Override
    public BranchDTO findById(String bid) throws Exception {
        try{


            Branch find = branchDAO.findById(bid);
            BranchDTO brnhdto = new BranchDTO(find.getBid(), find.getBname(), find.getBaddress());

            return brnhdto;
        }catch (HibernateException exp) {
            return null;
        }
    }
  
}
