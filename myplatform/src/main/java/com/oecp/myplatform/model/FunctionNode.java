package com.oecp.myplatform.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.oecp.myplatform.common.model.BaseEO;

/**
 * 功能节点
 * 
 * @author Changer
 *
 */
@Entity
@Table(name = "SYS_FUNCTION")
public class FunctionNode extends BaseEO {
	private static final long serialVersionUID = -6293722645353745492L;

	private String text;
	private List<FunctionNode> children;
	private FunctionNode parent;
	private String url;
	private int seqNo;
	@Transient
    private String parentid;
	 
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, orphanRemoval=false)
	public List<FunctionNode> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionNode> children) {
		this.children = children;
	}

	@ManyToOne
	public FunctionNode getParent() {
		return parent;
	}

	public void setParent(FunctionNode parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

}
