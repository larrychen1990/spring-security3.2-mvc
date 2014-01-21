package com.rr.springTest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Ross Romano
 */
@XmlRootElement(name="userInfo")
public class UserInfo implements Serializable
{
	private static final long serialVersionUID = -879346420806318807L;

	private String dn;

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	@Override
	public String toString() {
		return "UserInfo [dn=" + dn + "]";
	}

}
