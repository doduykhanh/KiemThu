module com.khoa.kiemthu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    opens com.khoa.kiemthu to javafx.fxml;
    exports com.khoa.kiemthu;
    exports Services;
}
