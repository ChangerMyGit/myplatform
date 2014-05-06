package com.oecp.myplatform.common.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形菜单VO
 * 
 * @author Changer
 *
 */
public class MenuNodeVO implements Serializable {
	private static final long serialVersionUID = -5169463695002137789L;
	private String text;
	private String id;
    private String state;
	private Map attributes;
	private List<MenuNodeVO> children;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map getAttributes() {
		return attributes;
	}

	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}

	public List<MenuNodeVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNodeVO> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
