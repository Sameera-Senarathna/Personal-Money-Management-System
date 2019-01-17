package lk.ijse.dep.individualProject.business.custom.impl;

import lk.ijse.dep.individualProject.business.BOFactory;
import lk.ijse.dep.individualProject.business.Converter;
import lk.ijse.dep.individualProject.business.custom.ReportBO;
import lk.ijse.dep.individualProject.dao.DAOFactory;
import lk.ijse.dep.individualProject.dao.custom.QueryDAO;
import lk.ijse.dep.individualProject.dto.TableReportDTO;
import lk.ijse.dep.individualProject.dto.TableReportDrillDTO;
import lk.ijse.dep.individualProject.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class ReportBOImpl implements ReportBO {

    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<TableReportDTO> getCategoryTotal(String type, int year, int month) throws Exception {
        return queryDAO.getCategoryTotal(type, year, month).map(new Function<List<CustomerEntity>, List<TableReportDTO>>() {
             @Override
             public List<TableReportDTO> apply(List<CustomerEntity> customerEntities) {
                 return Converter.getDTOList(customerEntities,TableReportDTO.class);
             }
         }).get();

    }

    @Override
    public List<TableReportDrillDTO> getSubCategoryTotal(int cId, int year, int month) throws Exception {
        return queryDAO.getSubCategoryTotal(cId,year,month).map(new Function<List<CustomerEntity>, List<TableReportDrillDTO>>() {
            @Override
            public List<TableReportDrillDTO> apply(List<CustomerEntity> customerEntities) {
                return Converter.getDTOList(customerEntities,TableReportDrillDTO.class);
            }
        }).get();

    }
}
