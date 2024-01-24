/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.khoa.kiemthu;

/**
 *
 * @author ACER
 */
public class HangHoa {
        private String id_hh;
    private String tenhang;
    private String giahang;
    private String xuatxu;
    private String mota;
    private String soluong;

    public HangHoa() {
    }

    public HangHoa(String id_hh, String tenhang, String giahang, String xuatxu, String mota, String soluong) {
        this.id_hh = id_hh;
        this.tenhang = tenhang;
        this.giahang = giahang;
        this.xuatxu = xuatxu;
        this.mota = mota;
        this.soluong = soluong;
    }
    
    
    /**
     * @return the id_hh
     */
    public String getId_hh() {
        return id_hh;
    }

    /**
     * @param id_hh the id_hh to set
     */
    public void setId_hh(String id_hh) {
        this.id_hh = id_hh;
    }

    /**
     * @return the tenhang
     */
    public String getTenhang() {
        return tenhang;
    }

    /**
     * @param tenhang the tenhang to set
     */
    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    /**
     * @return the giahang
     */
    public String getGiahang() {
        return giahang;
    }

    /**
     * @param giahang the giahang to set
     */
    public void setGiahang(String giahang) {
        this.giahang = giahang;
    }

    /**
     * @return the xuatxu
     */
    public String getXuatxu() {
        return xuatxu;
    }

    /**
     * @param xuatxu the xuatxu to set
     */
    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    /**
     * @return the mota
     */
    public String getMota() {
        return mota;
    }

    /**
     * @param mota the mota to set
     */
    public void setMota(String mota) {
        this.mota = mota;
    }

    /**
     * @return the soluong
     */
    public String getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

}
