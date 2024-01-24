/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.khoa.kiemthu;

import KhachHang.KhachHang;
import KhachHang.KhachHangService;
import Services.HangHoaService;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class UserController implements Initializable {
    @FXML Text vip;
    @FXML
    private TableView table3;
    @FXML
    private TableView table2;
    @FXML
    private Button inhoadon;

    @FXML
    private Button nhap;

    @FXML
    private TextField tenhang;

    @FXML
    private Button thanhtien;

    @FXML
    private TextField tienkhach;
    @FXML
    private Text idkhach;
    @FXML
    private Text Staff;
    @FXML
    private Text VND;
    @FXML
    private Button tra;
    @FXML 
    private Text tongtien;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextInputDialog a = new TextInputDialog();
        a.setHeaderText("Tên Nhân Viên: ");
        Optional<String> tenvn = a.showAndWait(); 
        Staff.setText(tenvn.get());
    }
//    public void staffSet(String i){
//        
//        Staff.setText(i);
//    }
    public void nhapSanPham(ActionEvent event) throws SQLException{
        String tenSP = tenhang.getText();
        
        String id = idkhach.getText();
        if(tenhang.getText().isEmpty()){
            ThongBao.getBox("Chưa nhập tên hàng", Alert.AlertType.WARNING).show();
        }
        ArrayList<HangHoa> hoadon= new ArrayList<>();
        ArrayList<HangHoa> sv = new ArrayList<>();
        HangHoaService a = new HangHoaService();
        sv = (ArrayList<HangHoa>) a.getHangHoa();
        table2.getColumns().clear();
        
        for(int i=0;i<sv.size();i++){
           if(tenSP.equals(sv.get(i).getTenhang()))
           {
               loadTableData2(tenSP);
               hoadon.add(sv.get(i));
               
               
           }   
        }
        loadtable2();
               
        
//        
        
        
    }
    public void loadtable2 (){
        
        TableColumn colName = new TableColumn("Tên");
        colName.setCellValueFactory(new PropertyValueFactory("tenhang"));
        colName.setPrefWidth(120);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("giahang"));
        colGia.setPrefWidth(120);
        
        TableColumn colXuatXu = new TableColumn("Xuất xứ");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("xuatxu"));
        colXuatXu.setPrefWidth(120);
        
        TableColumn colMoTa = new TableColumn("Giảm giá");
        colMoTa.setCellValueFactory(new PropertyValueFactory("mota"));
        colMoTa.setPrefWidth(120);
        
        TableColumn addColumn = new TableColumn();
        addColumn.setPrefWidth(120);
        addColumn.setCellFactory(p->{
            Button btn = new Button("Thêm");
            btn.setPrefWidth(120);
            btn.setOnAction(et ->{
                    Alert al = ThongBao.getBox("Bạn có muốn thêm mặt hàng này  vào hóa đơn không ?", Alert.AlertType.WARNING);
                    al.setContentText("Bạn có muốn thêm mặt hàng này  vào hóa đơn không ?");
                    al.showAndWait().ifPresent(res->{
                        TableCell cell = (TableCell)((Button)et.getSource()).getParent();
                        HangHoa han = (HangHoa) cell.getTableRow().getItem();
                        String idhh = han.getId_hh();
                        String namehh = han.getTenhang();
                        String giahh = han.getGiahang();
                        String xuatxu = han.getXuatxu();
                        String soluong = han.getSoluong();
                        String mota = han.getMota();
                        String idkh=idkhach.getText();
                        table3.getColumns().clear();
                        String tennv = Staff.getText();
                        double updateGia=0;
                        if(mota!=null)
                            updateGia = Double.valueOf(giahh) + Double.valueOf(mota);
                        
                        String loaikh = vip.getText();
                    try{
                        
                        TextInputDialog a = new TextInputDialog();
                        a.setHeaderText("Nhập số lượng/Kg: ");
                        Optional<String> sol = a.showAndWait();    
                        double luong = Double.parseDouble(sol.get());
                        HoaDonService.themHangHoaHD(UUID.randomUUID().toString(),tennv , idhh, namehh,  luong,String.valueOf(updateGia), 0,loaikh, mota,idkh);
//                      
                            loadTabledata3(idkh);
                            loadTable3();
                            
                            

                            ThongBao.getBox("Đã thêm ! ", Alert.AlertType.INFORMATION).show();
                        } catch (SQLException ex) {
                            ThongBao.getBox("Thêm thất bại  " +ex.getMessage(), Alert.AlertType.INFORMATION).show();
                        }
                    });    
                 }); 
                 
                TableCell cell = new TableCell();
                cell.setGraphic(btn);
                return cell;
        
        });
        
        this.table2.getColumns().addAll(colName,colGia,colXuatXu,colMoTa,addColumn);

    }
    public  void loadTable3(){
        TableColumn colName = new TableColumn("Tên");
        colName.setCellValueFactory(new PropertyValueFactory("namehh"));
        colName.setPrefWidth(120);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("giahh"));
        colGia.setPrefWidth(120);
        
        TableColumn colXuatXu = new TableColumn("Giảm giá");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("xuatxu"));
        colXuatXu.setPrefWidth(120);
        
        TableColumn colSoluong = new TableColumn("Số lượng/Kg");
        colSoluong.setCellValueFactory(new PropertyValueFactory("soluonghh"));
        colSoluong.setPrefWidth(120);
        TableColumn delColumn = new TableColumn();
        delColumn.setPrefWidth(120);
        delColumn.setCellFactory(p->{
            Button btn = new Button("Xóa");
            btn.setPrefWidth(120);
            btn.setOnAction(et ->{
                    Alert al = ThongBao.getBox("Bạn có muốn xóa mặt hàng này không ?", Alert.AlertType.WARNING);
                    al.setContentText("Bạn có muốn xóa mặt hàng này không ?");
                    al.showAndWait().ifPresent(res->{
                        String id = idkhach.getText();
                        TableCell cell = (TableCell)((Button)et.getSource()).getParent();
                        HoaDon hang = (HoaDon) cell.getTableRow().getItem();
                    try{
                        HoaDonService.deleteHangHoaDon(hang.getIdhd());
                        this.table3.getItems().clear();
                        this.table3.setItems(FXCollections.observableArrayList(HoaDonService.napHoaDon(id)));
                        ThongBao.getBox("Xoa thanh cong ", Alert.AlertType.INFORMATION).show();
                        } catch (SQLException ex) {
                            ThongBao.getBox("Xóa Thất Bại  " +ex.getMessage(), Alert.AlertType.INFORMATION).show();
                        }
                    });    
                 });     
                TableCell cell = new TableCell();
                cell.setGraphic(btn);
                return cell;
        
        });
        this.table3.getColumns().addAll(colName,colGia,colXuatXu,colSoluong,delColumn);
    
    }
    private void loadTableData2(String kw) throws SQLException{
        HangHoaService d = new HangHoaService();
        this.table2.setItems(FXCollections.observableList(d.napHangHoa(kw)));
    }
    
    public void loadTabledata3(String id) throws SQLException{
        
        HoaDonService b = new HoaDonService();
         this.table3.setItems(FXCollections.observableList(b.napHoaDon(id)));
//        ArrayList<HoaDon> d = new ArrayList<>();
//        ArrayList<HoaDon> c = (ArrayList<HoaDon>) b.getHoaDon();
//        for(int i=0; i < c.size();i++){
//            if(id.equals(c.get(i).getIdkh()));
//                d.add(c.get(i));
//               
        }
       
       
    public  double tinhTienBTN(String idkh) throws SQLException{
        double thanhtien = 0;
        double tienkh;
        String khvip = vip.getText();

        tienkh = Double.parseDouble(tienkhach.getText());
        HoaDonService a = new HoaDonService();
        ArrayList<HoaDon> b= (ArrayList<HoaDon>) a.napHoaDon(idkhach.getText());
        for(int i=0;i<b.size();i++){
                
                thanhtien+= Double.parseDouble(b.get(i).getGiahh())*Double.parseDouble(b.get(i).getSoluonghh());
        }
        
        
           

        
        return thanhtien;
    }
    public void tinhTien(ActionEvent event) throws SQLException{
        if(tienkhach.getText().isBlank()){
            ThongBao.getBox("Chưa nhập tiền khách hàng đưa !", Alert.AlertType.WARNING).show();
        }
        String khvip = vip.getText();
        String tienk = tienkhach.getText();
        String idkh = idkhach.getText(); 
        double tienthoi =0;
        if(Double.parseDouble(tienkhach.getText())<0)
            ThongBao.getBox("Số tiền không được âm !", Alert.AlertType.WARNING).show();
        
        if(Double.parseDouble(tienk)<tinhTienBTN(idkh))
            ThongBao.getBox("Tiền khách đưa không đủ !", Alert.AlertType.WARNING).show();
        if(khvip.matches("VIP"))
        tongtien.setText(String.valueOf(tinhTienBTN(idkh)-(tinhTienBTN(idkh)*8/100)));
        else
            tongtien.setText(String.valueOf(tinhTienBTN(idkh)));
        String tt = tongtien.getText();
        HoaDonService nap = new HoaDonService();
        tienthoi= Double.parseDouble(tienk)-Double.parseDouble(tt);
        VND.setText(String.valueOf(tienthoi));
         ArrayList<HoaDon> hd = (ArrayList<HoaDon>) nap.napHoaDon(idkh);
         for(int i =0; i<hd.size();i++)
         {            
                 HoaDonService.update(hd.get(i).getIdhd(), hd.get(i).getNamenv(), hd.get(i).getIdhd(), hd.get(i).getNamehh(),
                         hd.get(i).getSoluonghh(), hd.get(i).getGiahh(),tt, hd.get(i).getLoaikhach(), hd.get(i).getXuatxu(),idkh);
         }
  
    }
    public void inHD(ActionEvent evnt) throws SQLException, IOException{
        
        int idkhachhang = Integer.parseInt(idkhach.getText());
        String idk = idkhach.getText();

        HoaDonService nap = new HoaDonService();
         ArrayList<HoaDon> hd = (ArrayList<HoaDon>) nap.napHoaDon(idk);
         try {
            xuatHD();
            ThongBao.getBox("Xuat hoa don thanh cong !", Alert.AlertType.INFORMATION).show();
        } catch (Exception e) {
            if(tienkhach.getText()==null)
                ThongBao.getBox("Xuat hoa don khong thanh cong !", Alert.AlertType.WARNING).show();
        }
         
         for(int i =0; i<hd.size();i++)
         {            
                 HoaDonService.update(hd.get(i).getIdhd(), hd.get(i).getNamenv(), hd.get(i).getIdhd(), hd.get(i).getNamehh(),
                         hd.get(i).getSoluonghh(), hd.get(i).getGiahh(),String.valueOf(hd.get(i).getThanhtien()), hd.get(i).getLoaikhach(), null, "Đã tính");
         }
        
        idkhachhang+=1;
        vip.setText("");
        idkhach.setText(String.valueOf(idkhachhang));
        table2.getItems().clear();
        table3.getItems().clear();
        tienkhach.setText("");
        tenhang.setText("");
        tongtien.setText("");
        VND.setText("");
        
    }
    public void xuatHD() throws IOException, SQLException{
        String id = idkhach.getText();
        String nhanvien = Staff.getText();
        FileWriter hoadon = new FileWriter("HoaDon"+id+".txt");
        HoaDonService nap = new HoaDonService();
        ArrayList<HoaDon> h = (ArrayList<HoaDon>) nap.napHoaDon(id);
        hoadon.write("371 Nguyen Kiem, Go Vap, Ho Chi Minh\n");
        hoadon.write("=====================================\n");
        hoadon.write("==============Hoa Don================\n");
        hoadon.write("Nhan vien : "+ nhanvien +"\n");
        hoadon.write("\n");
        hoadon.write("=====================================\n");
        hoadon.write("Ten   Soluong   Khuyen mai    Gia \n");
        for(int i =0; i<h.size();i++)
         {
             hoadon.write(h.get(i).getNamehh()+"      ");
             hoadon.write(h.get(i).getSoluonghh()+"       ");
             hoadon.write(h.get(i).getXuatxu()+"         ");
             hoadon.write(h.get(i).getGiahh()+"      \n");
             hoadon.write("=====================================\n");
         }
        if(vip.getText().isBlank())
            hoadon.write("      \n");
        else
            hoadon.write("VIP -8% Tong tien     \n");
        hoadon.write("=====================================\n");
        String tt = tongtien.getText();
        String khachdua = tienkhach.getText();
        String thoilai = VND.getText();
        String tienvip = vip.getText();
        hoadon.write("\n");
        hoadon.write("\n");
        hoadon.write("Tien khach dua: "+ khachdua+" Dong"+"\n");
        hoadon.write("Thanh tien: "+tt+" Dong"+"\n");
        hoadon.write("Tien thoi lai: "+ thoilai+" Dong"+"\n");
        hoadon.write("\n");
        hoadon.write("          CAM ON QUY KHACH DA MUA HANG       \n");
        hoadon.close();
    }
    public void traCuu(ActionEvent event) throws SQLException{
         String id = idkhach.getText();
        TextInputDialog a = new TextInputDialog();
       
        a.setHeaderText("Nhập tên khách hang: =");
        Optional<String> sol = a.showAndWait(); 
        KhachHangService d = new KhachHangService();
        ArrayList<KhachHang> c = new ArrayList<>();
        c = (ArrayList<KhachHang>) d.search(sol.get());
        
            for (int i = 0; i < c.size(); i++) {
               
                    if(sol.get().equals(c.get(i).getTenkh())){                   
                
                     
                     vip.setText("VIP");
                    
                     ThongBao.getBox("Khách hàng này là thành viên", Alert.AlertType.INFORMATION).show();
        
                        }  
                    else
                    {
                        vip.setText("");
                        ThongBao.getBox("Khách hàng này không là thành viên", Alert.AlertType.WARNING).show();
                    }

        
   
        
            }
    }

    
        
}
