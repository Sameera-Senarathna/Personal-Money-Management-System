package lk.ijse.dep.individualProject.dao.custom.impl;

import lk.ijse.dep.individualProject.dao.CrudUtil;
import lk.ijse.dep.individualProject.dao.custom.SubCategoryDAO;
import lk.ijse.dep.individualProject.entity.SubCategory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubCategoryDAOImpl implements SubCategoryDAO {

    @Override
    public Optional<SubCategory> find(Integer key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM SubCategory WHERE scId=?",key);
        SubCategory subCategory = null;
        if(rst.next()){
            subCategory = new SubCategory(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4));
        }
        return Optional.ofNullable(subCategory);
    }

    @Override
    public Optional<List<SubCategory>> findAll() throws Exception {
        List<SubCategory> allSubCategories = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM SubCategory");
        while (rst.next()){
            allSubCategories.add(new SubCategory(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)));
        }
        return Optional.ofNullable(allSubCategories);
    }

    @Override
    public boolean save(SubCategory entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO SubCategory (scName,scDescription,cId) VALUES(?,?,?)",entity.getScName(),entity.getScDescription(),entity.getcId())>0;
    }

    @Override
    public boolean update(SubCategory entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE SubCategory SET scName=? , scDescription=? , cId=? WHERE scId=?",entity.getScName(),entity.getScDescription(),entity.getcId(),entity.getScId())>0;
    }

    @Override
    public boolean delete(Integer key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM SubCategory WHERE scId=?",key)>0;
    }

    @Override
    public Optional<List<SubCategory>> getSubCategoryByCategoryId(int categoryID) throws Exception {
        List<SubCategory> allSubCategories = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM SubCategory WHERE cId=?",categoryID);
        while (rst.next()){
            allSubCategories.add(new SubCategory(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)));
        }
        return Optional.ofNullable(allSubCategories);
    }
}
