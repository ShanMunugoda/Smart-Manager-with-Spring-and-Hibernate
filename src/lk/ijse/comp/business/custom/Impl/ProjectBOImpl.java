/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.comp.business.custom.Impl;

import lk.ijse.comp.business.custom.ProjectBO;
import lk.ijse.comp.dao.custom.BranchDAO;
import lk.ijse.comp.dao.custom.ProjectDAO;
import lk.ijse.comp.dto.BranchDTO;
import lk.ijse.comp.dto.ProjectDTO;
import lk.ijse.comp.entity.Branch;
import lk.ijse.comp.entity.Project;
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
public class ProjectBOImpl implements ProjectBO {
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private BranchDAO branchDAO;


    public ProjectBOImpl() {
//projectDAO= AppInitializer.ctx.getBean(ProjectDAOImpl.class);
//branchDAO=AppInitializer.ctx.getBean(BranchDAOImpl.class);

//        sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public boolean saveProject(ProjectDTO dto) throws Exception {
        try{

            Project project=new Project(dto.getPid(),dto.getPname(),dto.getStartdate(),dto.getEnddate(),new Branch(dto.getBid()));
//            Project project=new Project();
//            project.setPid(dto.getPid());
//            project.setPname(dto.getPname());
//            project.setStartdate(dto.getStartdate());
//            project.setEnddate(dto.getEnddate());
//
//            Branch branch=new Branch();
//            branch.setBid(dto.getBid());

//            project.setBranch(branch);
         projectDAO.Save(project);

            return true;

        }catch (HibernateException exp){
            return false;
        }
    }






    @Override
    public boolean updateProject(ProjectDTO dto) throws Exception {
//        Project pjct = new Project(dto.getPid(), dto.getPname(), dto.getStartdate(), dto.getEnddate(), dto.getBid());
//        boolean rst = projectDAO.Update(pjct);
//        return rst;
        return false;
    }

    @Override
    public boolean deleteProject(String id) throws Exception {
       try{

           projectDAO.Delete(id);

           return true;
       }catch (HibernateException exp){
           return false;
       }
    }

    @Override
    public ArrayList<ProjectDTO> allProject() throws Exception {

        try{

            ArrayList<ProjectDTO> arr = new ArrayList<>();

            List<Project> all = projectDAO.getAll();
            for (Project project : all) {
                ProjectDTO projectDTO = new ProjectDTO(project.getPid(), project.getPname(), project.getStartdate(), project.getEnddate(), project.getBranch().getBid());
                arr.add(projectDTO);
            }

            return arr;
        }catch (HibernateException exp){
            return null;
        }

    }

    @Override
    public ProjectDTO findById(String pid) throws Exception {
        try{


            Project find = projectDAO.findById(pid);
            ProjectDTO projectDTO = new ProjectDTO(find.getPid(), find.getPname(), find.getStartdate(), find.getEnddate(), find.getBranch().getBid());



            return projectDTO;
        }catch (HibernateException exp){
            return null;
        }
    }


    @Override
    public ArrayList<BranchDTO> allBids() throws Exception {
        try  {



            List<Branch> all = branchDAO.getAll();



            ArrayList<BranchDTO> arr = new ArrayList<>();

            for (Branch branch : all) {

                String bid = branch.getBid();
                BranchDTO branchDTO = new BranchDTO(bid);
                arr.add(branchDTO);
            }

            return arr;
        }catch (HibernateException exp){
            return null;
        }



    }


   
}
