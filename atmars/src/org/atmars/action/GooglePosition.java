/*
 * atmars, An Implementation of a Micro Blog
 * Copyright (C) 2013, Cloudzfy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.atmars.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.opensymphony.xwork2.ActionSupport;

public class GooglePosition extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String latitude;
	private String longitude;
	private String position;

	@Override
	public String execute() {
		String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
				+ this.latitude + "," + this.longitude + "&sensor=true";

		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			InputStreamReader insr = new InputStreamReader(
					httpsConn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(insr);
			String data = new String();
			String bufferString = br.readLine();

			while (bufferString != null) {
				data += bufferString;
				data += "\n";
				bufferString = br.readLine();
			}
			position = new String(data);
			System.out.println(position);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
