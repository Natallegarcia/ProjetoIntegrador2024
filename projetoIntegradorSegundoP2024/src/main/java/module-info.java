module com.projetointegrador.projetointegradorsegundop2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
     

    opens com.projetointegrador.projetointegradorsegundop2024 to javafx.fxml;
    exports com.projetointegrador.projetointegradorsegundop2024;
}
