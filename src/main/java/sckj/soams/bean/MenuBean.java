package sckj.soams.bean;

import java.util.List;

public class MenuBean {
	private String menuId;
	
	private String menuText;
	
	private String menuUrl;
	
	private String menuIcon;
	
	List<MenuBean> childMenus;

	
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public List<MenuBean> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuBean> childMenus) {
		this.childMenus = childMenus;
	}
	
}
