package com.world.covid.dto;

import java.io.Serializable;

public class MenuDto implements Serializable {

    protected Long menuId;
    protected String menuName;
    protected Long parentMenuId;
    protected Boolean isLeaf;
    protected String interfaceId;
    protected String interfaceName;
    protected String actionPath;

    public MenuDto() {
    }

    public MenuDto(Long menuId, String menuName, Long parentMenuId, Boolean isLeaf, String interfaceId, String interfaceName, String actionPath) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentMenuId = parentMenuId;
        this.isLeaf = isLeaf;
        this.interfaceId = interfaceId;
        this.interfaceName = interfaceName;
        this.actionPath = actionPath;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getActionPath() {
        return actionPath;
    }

    public void setActionPath(String actionPath) {
        this.actionPath = actionPath;
    }
}
