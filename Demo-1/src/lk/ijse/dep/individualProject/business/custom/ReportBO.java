package lk.ijse.dep.individualProject.business.custom;

import lk.ijse.dep.individualProject.business.SuperBO;
import lk.ijse.dep.individualProject.dto.TableReportDTO;
import lk.ijse.dep.individualProject.dto.TableReportDrillDTO;

import java.util.List;

public interface ReportBO extends SuperBO {

    List<TableReportDTO> getCategoryTotal(String type, int year, int month) throws Exception;

    List<TableReportDrillDTO> getSubCategoryTotal(int scId, int year, int month) throws Exception;



}
