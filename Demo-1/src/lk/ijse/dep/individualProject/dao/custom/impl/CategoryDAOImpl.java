package lk.ijse.dep.individualProject.dao.custom.impl;

import javafx.beans.binding.When;
import lk.ijse.dep.individualProject.dao.CrudUtil;
import lk.ijse.dep.individualProject.dao.custom.CategoryDAO;
import lk.ijse.dep.individualProject.entity.Category;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public Optional<Category> find(Integer key) throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT * FROM Category WHERE cId=?",key);
        Category category = null;
        if(rst.next()){
            category = new Category(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<List<Category>> findAll() throws Exception {

        List<Category> allCategory = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Category");
        while (rst.next()){
            allCategory.add(new Category(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }

        return Optional.ofNullable(allCategory);
    }

    @Override
    public boolean save(Category entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Category (cName,cDescription,cType) VALUES(?,?,?)",entity.getcName(),entity.getcDescription(),entity.getcType())>0;
    }

    @Override
    public boolean update(Category entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Category SET cName=? , cDescription=? , cType=? WHERE cId=?",
                entity.getcName(),entity.getcDescription(),entity.getcType(),entity.getcId())>0;
    }

    @Override
    public boolean delete(Integer key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Category WHERE cId=?",key)>0;
    }
}
