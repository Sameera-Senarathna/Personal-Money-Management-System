package lk.ijse.dep.individualProject.business.custom.impl;

import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.Converter;
import lk.ijse.dep.individualProject.business.custom.CategoryBO;
import lk.ijse.dep.individualProject.business.custom.SubCategoryBO;
import lk.ijse.dep.individualProject.dao.DAOFactory;
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.dao.custom.SubCategoryDAO;
import lk.ijse.dep.individualProject.dto.SubCategoryDTO;
import lk.ijse.dep.individualProject.dto.SubCategoryTableDTO;
import lk.ijse.dep.individualProject.entity.CustomerEntity;
import lk.ijse.dep.individualProject.entity.SubCategory;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SubCategoryBOImpl implements SubCategoryBO {

   SubCategoryDAO subCategoryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUBCATEGORY);
   QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);


    @Override
    public boolean addSubCategory(SubCategoryDTO subCategoryDTO) throws Exception {
        return subCategoryDAO.save(Converter.getEntity(subCategoryDTO));
    }

    @Override
    public List<SubCategoryTableDTO> getAllSubCategory() throws Exception {
        Optional<List<CustomerEntity>> list = queryDAO.getAllSubCategoriesWithCategoryNames();
        return list.map(new Function<List<CustomerEntity>, List<SubCategoryTableDTO>>() {
            @Override
            public List<SubCategoryTableDTO> apply(List<CustomerEntity> customerEntities) {
                return Converter.getDTOList(customerEntities,SubCategoryTableDTO.class);
            }
        }).get();
    }

    @Override
    public boolean updateCategory(SubCategoryDTO subCategoryDTO) throws Exception {
        return subCategoryDAO.update(Converter.getEntity(subCategoryDTO));
    }

    @Override
    public boolean deletesubCategory(int key) throws Exception {
        return subCategoryDAO.delete(key);
    }

    @Override
    public List<SubCategoryDTO> getSubCategoryByCategoryID(int categoryID) throws Exception {
       return subCategoryDAO.getSubCategoryByCategoryId(categoryID).map(new Function<List<SubCategory>, List<SubCategoryDTO>>() {
            @Override
            public List<SubCategoryDTO> apply(List<SubCategory> subCategories) {
                return Converter.getDTOList(subCategories);
            }
        }).get();

    }


}
