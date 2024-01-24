/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.khoa.kiemthu;

/**
 *
 * @author ACER
 */
public class HoaDon {

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

    
    private String idhd;
    private String namenv;
    private String idhh;
    private String namehh;
    private String soluonghh;
    private String giahh;
    private double thanhtien;
    private String loaikhach;
    private String xuatxu;
    private String idkh;

    public HoaDon() {
    }

    public HoaDon(String idhd, String namenv, String idhh, String namehh, String soluonghh, String giahh, double thanhtien, String loaikhach,String xuatxu,String idkh) {
        this.idhd = idhd;
        this.namenv = namenv;
        this.idhh = idhh;
        this.namehh = namehh;
        this.soluonghh = soluonghh;
        this.giahh = giahh;
        this.thanhtien = thanhtien;
        this.loaikhach = loaikhach;
        this.xuatxu=xuatxu;
        
    }
    
    public String getIdhd() {
        return idhd;
    }

    /**
     * @param idhd the idhd to set
     */
    public void setIdhd(String idhd) {
        this.idhd = idhd;
    }

    /**
     * @return the namenv
     */
    public String getNamenv() {
        return namenv;
    }

    /**
     * @param namenv the namenv to set
     */
    public void setNamenv(String namenv) {
        this.namenv = namenv;
    }

    /**
     * @return the idhh
     */
    public String getIdhh() {
        return idhh;
    }

    /**
     * @param idhh the idhh to set
     */
    public void setIdhh(String idhh) {
        this.idhh = idhh;
    }

    /**
     * @return the namehh
     */
    public String getNamehh() {
        return namehh;
    }

    /**
     * @param namehh the namehh to set
     */
    public void setNamehh(String namehh) {
        this.namehh = namehh;
    }

    /**
     * @return the soluonghh
     */
    public String getSoluonghh() {
        return soluonghh;
    }

    /**
     * @param soluonghh the soluonghh to set
     */
    public void setSoluonghh(String soluonghh) {
        this.soluonghh = soluonghh;
    }

    /**
     * @return the giahh
     */
    public String getGiahh() {
        return giahh;
    }

    /**
     * @param giahh the giahh to set
     */
    public void setGiahh(String giahh) {
        this.giahh = giahh;
    }

    /**
     * @return the thanhtien
     */
    public double getThanhtien() {
        return thanhtien;
    }

    /**
     * @param thanhtien the thanhtien to set
     */
    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    /**
     * @return the loaikhach
     */
    public String getLoaikhach() {
        return loaikhach;
    }

    /**
     * @param loaikhach the loaikhach to set
     */
    public void setLoaikhach(String loaikhach) {
        this.loaikhach = loaikhach;
    }

    
}
