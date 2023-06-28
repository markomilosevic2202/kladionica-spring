package marko.kladionica.service;

import marko.kladionica.entity.Reports;

import java.util.List;
import java.util.Optional;

public interface ReportsService {

    public void save(String address, String nameUser);

    public List<Reports> getList();

    public List<Reports> getListByUser(String name);
}
