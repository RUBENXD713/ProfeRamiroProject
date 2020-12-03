package com.example.proyectoproferamiro;

public class Permiso {
    private String permiso;
    private boolean acceso;
    private String permisoReal;

    public Permiso(String permiso, boolean acceso, String peermisoReal) {
        this.permiso = permiso;
        this.acceso = acceso;
        this.permisoReal = peermisoReal;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public boolean getAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public String getPeermisoReal() {
        return permisoReal;
    }

    public void setPeermisoReal(String peermisoReal) {
        this.permisoReal = peermisoReal;
    }
}