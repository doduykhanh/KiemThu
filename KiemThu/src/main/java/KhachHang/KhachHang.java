/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

/**
 *
 * @author ACER
 */
public class KhachHang {

    private String idkh;
    private String tenkh;
    private String snkh;

    public KhachHang() {
    }

    public KhachHang(String idkh, String tenkh, String snkh) {
        this.idkh = idkh;
        this.tenkh = tenkh;
        this.snkh = snkh;
    }
    
    
    
    /**
     * @return the idkh
     */
    public String getIdkh() {
        return idkh;
    }

    /**
     * @param idkh the idkh to set
     */
    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    /**
     * @return the tenkh
     */
    public String getTenkh() {
        return tenkh;
    }

    /**
     * @param tenkh the tenkh to set
     */
    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    /**
     * @return the snkh
     */
    public String getSnkh() {
        return snkh;
    }

    /**
     * @param snkh the snkh to set
     */
    public void setSnkh(String snkh) {
        this.snkh = snkh;
    }

}
