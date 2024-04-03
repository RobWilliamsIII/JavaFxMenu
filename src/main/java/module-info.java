module javafxmenu.javafxmenu {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafxmenu.javafxmenu to javafx.fxml;
    exports javafxmenu.javafxmenu;
}