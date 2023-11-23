package ma.projet.domain;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;

import ma.projet.service.ServiceService;
import ma.projet.services.EmployeService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "EmployeBean")
public class EmployeBean {
    private Employe Employe;
    private Service service;
    private List<Employe> Employes;
    private List<Employe> EmployesBetweenDates;
    private EmployeService EmployeService;
    private ServiceService serviceService;
    private List<Employe> chefs;
    private Employe selectedChef;
    private static ChartModel barModel;
    private Date date1;
    private Date date2;
    

    public EmployeBean() {
        Employe = new Employe();
            Employe.setService(new Service());
        EmployeService = new EmployeService();
        serviceService = new ServiceService();
        chefs = EmployeService.getAll();
        selectedChef = new Employe();
    }

    public List<Employe> getEmployes() {
        if (Employes == null) {
            Employes = EmployeService.getAll();
        }
        return Employes;
    }

    public void setEmployes(List<Employe> Employes) {
        this.Employes = Employes;
    }

    public EmployeService getEmployeService() {
        return EmployeService;
    }

    public void setEmployeService(EmployeService EmployeService) {
        this.EmployeService = EmployeService;
    }

    public Employe getEmploye() {
        return Employe;
    }

    public void setEmploye(Employe Employe) {
        this.Employe = Employe;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String onCreateAction() {
   EmployeService employeService = new EmployeService();

    // Set the selected chef to the employe
    Employe.setChef(selectedChef);

    // Create the employe
    employeService.create(Employe);

    // Reset employe and selectedChef
    Employe = new Employe();
    selectedChef = null;

    return null;
    }

    public String onDeleteAction() {
        EmployeService.delete(EmployeService.getById(Employe.getId()));
        return null;
    }

    public List<Employe> serviceLoad() {
        for (Employe e : EmployeService.getAll()) {
            if (e.getService().equals(service)) {
                Employes.add(e);
            }
        }
        return Employes;
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getEmployes();
    }

    public void onEdit(RowEditEvent event) {
        Employe = (Employe) event.getObject();
        Service service = serviceService.getById(this.Employe.getService().getId());
        Employe.setService(service);
        Employe.getService().setNom(service.getNom());
        EmployeService.update(Employe);
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

public ChartModel initBarModel() {
    CartesianChartModel model = new CartesianChartModel();
    ChartSeries Employes = new ChartSeries();
    Employes.setLabel("Employes");
    model.setAnimate(true);

    for (Object[] e : EmployeService.nbEmployeByChef()) {
        Employes.set((String) e[1], Integer.parseInt(e[0].toString()));
    }

    model.addSeries(Employes);

    return model;
}



    public List<Employe> EmployeLoad() {
        EmployesBetweenDates = EmployeService.getbydates(date1, date2);
        return null;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Employe> getEmployesBetweenDates() {
        return EmployesBetweenDates;
    }

    public void setEmployesBetweenDates(List<Employe> EmployesBetweenDates) {
        this.EmployesBetweenDates = EmployesBetweenDates;
    }
    public List<Employe> getChefs() {
    return chefs;
}

public Employe getSelectedChef() {
    return selectedChef;
}

public void setSelectedChef(Employe selectedChef) {
    this.selectedChef = selectedChef;
}

}