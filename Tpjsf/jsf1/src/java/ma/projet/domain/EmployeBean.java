package ma.projet.domain;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;

import ma.projet.services.ServiceService;
import ma.projet.services.EmployeService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "EmployeBean")
public class EmployeBean {

    private Employe Employe;
    private Service service;
    private List<Employe> Employes;
    private List<Employe> EmployesBetweenDates;
    private EmployeService employeService;
    private ServiceService serviceService;
    private List<Employe> chefs;
    private Employe selectedChef;
    private static ChartModel barModel;
    private Date date1;
    private Date date2;
    private Employe employe;
    private TreeNode root;

    public EmployeBean() {
        Employe = new Employe();
        Employe.setService(new Service());
        employeService = new EmployeService();
        serviceService = new ServiceService();
        chefs = employeService.getAll();
        selectedChef = new Employe();
        root = new DefaultTreeNode("root", null);
        loadTree();
    }

    public List<Employe> getEmployes() {
        if (Employes == null) {
            Employes = employeService.getAll();
        }
        return Employes;
    }

    public void setEmployes(List<Employe> Employes) {
        this.Employes = Employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService EmployeService) {
        this.employeService = EmployeService;
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
        employeService.delete(employeService.getById(Employe.getId()));
        return null;
    }

    public List<Employe> serviceLoad() {
        for (Employe e : employeService.getAll()) {
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
        employeService.update(Employe);
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

        for (Object[] e : employeService.nbEmployeByChef()) {
            Employes.set((String) e[1], Integer.parseInt(e[0].toString()));
        }

        model.addSeries(Employes);

        return model;
    }

    public List<Employe> EmployeLoad() {
        EmployesBetweenDates = employeService.getbydates(date1, date2);
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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

//     public void loadTree() {
//    root.getChildren().clear(); // Clear old nodes
//    List<Service> services = serviceService.getAll();
//
//    for (Service service : services) {
//        TreeNode serviceNode = new DefaultTreeNode(service, root);
//
//        // Employees nodes
//        for (Employe employe : employeService.employessByService(service)) {
//            TreeNode employeNode = new DefaultTreeNode(employe, serviceNode);
//
//            // Chef node
//            Employe chef = employe.getChef() ;
//            if (chef != null) {
//                TreeNode chefNode = new DefaultTreeNode(chef, employeNode);
//            }
//        }
//    }
//}
    public void loadTree() {
        root.getChildren().clear(); // Clear old nodes
        List<Service> services = serviceService.getAll();

        for (Service service : services) {
            // Create a node for the service
            TreeNode serviceNode = new DefaultTreeNode(service, root);

            // Display chief under service
            Employe chief = service.getChef();
            if (chief != null) {
                // Create a node for the chief under the service node
                TreeNode chiefNode = new DefaultTreeNode(chief, serviceNode);

                // Display employees under chief
                for (Employe employe : service.getEmployes()) {
                    // Create a node for each employee under the chief node
                    TreeNode employeNode = new DefaultTreeNode(employe , chiefNode);
                }

            }
        }
    }
}
