package su.tsc.reports.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import su.tsc.reports.backend.entity.*;
import su.tsc.reports.backend.service.UnitService;
import su.tsc.reports.util.DateTimeUtil;

@Route("")
@CssImport("./styles/shared-styles.css")
//@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("ТСК | Обслуживание техники")
public class MainView extends VerticalLayout {

    final static long serialVersionUID = 2356040513043504611L;

    private final UnitService unitService;
    private final Grid<Unit> grid = new Grid<>(Unit.class);

    private final Button excelButton = new Button("Export to excel");
    private final TextField serialFilter = new TextField();
    private final TextField branchFilter = new TextField();
    private final TextField curatorFilter = new TextField();
    private final DatePicker shipmentDateFilter = new DatePicker("С поставки от..");
    private final Checkbox withoutServedFilter = new Checkbox("Без обслуживания");
    private final Checkbox onGuaranteeFilter = new Checkbox("На гарантии");
    private final Checkbox withoutContractFilter = new Checkbox("Без контракта");
    private final Checkbox withNotesFilter = new Checkbox("С заметками руководителя");

    public MainView(UnitService unitService) {
        this.unitService = unitService;
        addClassName("list-view");
        setSizeFull();
        configureFilters();
        configureGrid();
        HorizontalLayout headerLayout = new HorizontalLayout(
                new H3("ТСК: Обслуживание техники")
        );
        HorizontalLayout filterLayout = new HorizontalLayout(
                excelButton, serialFilter, branchFilter, curatorFilter, shipmentDateFilter,
                withoutServedFilter, onGuaranteeFilter, withoutContractFilter, withNotesFilter
        );
        filterLayout.setDefaultVerticalComponentAlignment(Alignment.END);

        add(headerLayout);
        add(filterLayout);
        add(grid);

        updateList();
    }

    private void configureFilters() {
        serialFilter.setPlaceholder("Серийник..");
        serialFilter.setClearButtonVisible(true);
        serialFilter.setValueChangeMode(ValueChangeMode.LAZY);
        serialFilter.addValueChangeListener(e -> updateList());

        branchFilter.setPlaceholder("Филиал..");
        curatorFilter.setPlaceholder("Куратор..");
    }

    private void updateList() {
        grid.setItems(unitService.findAll(serialFilter.getValue())); // TODO
    }

    private void configureGrid() {
        grid.addClassName("unit-grid");
        grid.setSizeFull();

        grid.setColumns("serial");
        grid.getColumnByKey("serial").setHeader("Серийник");

        grid.addColumn((unit) -> unit.getModel().getName()).setHeader("Модель").setSortable(true);

        grid.addColumn((unit) -> unit.getBrand().getName()).setHeader("Бренд")
                .setSortable(true).setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> unit.getBranch().getName()).setHeader("Филиал").setSortable(true);

        grid.addColumn((unit) -> {
            Emploee curator = unit.getCurator();
            // TODO (парсить при загрузке файа) return curator.getName();
            return "Иванов В.А.";
        }).setHeader("Куратор").setSortable(true);

        grid.addColumn((unit) -> unit.getResponsible().getName()).setHeader("Ответственный").setSortable(true);
        grid.addColumn((unit) -> unit.getOwner().getName()).setHeader("Владелец");

        grid.addColumn((unit) -> DateTimeUtil.offsetDateTimeToString(unit.getShipmentDate()))
                .setHeader("Дата поставки").setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn(unit -> {
            Counter counter = unit.getCounter();
            if (counter.getLastValue() == 0) {
                return "";
            }
            return DateTimeUtil.offsetDateTimeToString(counter.getDateFixing()) + " / " + counter.getLastValue();
        }).setHeader("Счетчик").setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> {
            Company buyer = unit.getBuyer();
            return buyer == null ? "" : buyer.getName();
        }).setHeader("Плательщик");

        grid.addColumn((unit) -> DateTimeUtil.offsetDateTimeToString(unit.getServiceData()
                .getLastDateService())).setHeader("Дата последнего ТО")
                .setTextAlign(ColumnTextAlign.CENTER)
        ;
        grid.addColumn((unit) -> unit.getServiceData().getPlanServiceCount() + " / " +
                unit.getServiceData().getFactServiceCount()).setHeader("Кол ТО план / факт")
                .setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> {
            ServiceData serviceData = unit.getServiceData();
            return serviceData.isServed() ? "Да" : "Нет";
        }).setHeader("Обслуживается").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> {
            Guarantee guarantee = unit.getGuarantee();
            String result = guarantee.isGuarantee() ? "Да" : "Нет";
            if (!guarantee.getGuaranteeLetter().isEmpty()) {
                result += " письмо";
            }
            return result;
        }).setHeader("Гарантия").setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> unit.hasContract() ? "Да" : "Нет").setHeader("Контракт")
                .setSortable(true).setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> unit.getConnection().getCallsCount() + " / " +
                unit.getConnection().getDeparturesCount())
                .setHeader("Звонки / Выезды").setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> {
            Repair repair = unit.getRepair();
            String result = repair.getRepairsCount() + " >>>"; // TODO подставить ссылку repair.getRepairsLink()
            return result;
        }).setHeader("ЗнР").setTextAlign(ColumnTextAlign.CENTER);

        grid.addColumn((unit) -> {
            Reason reason = unit.getReason(); // TODO парсить при загрузке данных
            return "";
        }).setHeader("Причины");

        grid.addThemeNames("no-row-borders", "row-stripes", "no-headers");

        for (String theme : grid.getThemeNames()) {
            System.out.println(theme);
        }

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }



}
