package application;

import java.sql.Date;
import java.time.LocalDateTime;

public class GetData {
private int id;
private String burimi;
private String destinacioni;
private int vende;
private String klasa;
private LocalDateTime data;
private float cmimi;
private String kohezgjatja;

public GetData(int id, String burimi, String destinacioni, int vende, String klasa, LocalDateTime data, float cmimi,
		String kohezgjatja) {
	super();
	this.id = id;
	this.burimi = burimi;
	this.destinacioni = destinacioni;
	this.vende = vende;
	this.klasa = klasa;
	this.data = data;
	this.cmimi = cmimi;
	this.kohezgjatja = kohezgjatja;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBurimi() {
	return burimi;
}
public void setBurimi(String burimi) {
	this.burimi = burimi;
}
public String getDestinacioni() {
	return destinacioni;
}
public void setDestinacioni(String destinacioni) {
	this.destinacioni = destinacioni;
}
public int getVende() {
	return vende;
}
public void setVende(int vende) {
	this.vende = vende;
}
public String getKlasa() {
	return klasa;
}
public void setKlasa(String klasa) {
	this.klasa = klasa;
}
public LocalDateTime getData() {
	return data;
}
public void setData(LocalDateTime data) {
	this.data = data;
}
public float getCmimi() {
	return cmimi;
}
public void setCmimi(float cmimi) {
	this.cmimi = cmimi;
}
public String getKohezgjatja() {
	return kohezgjatja;
}
public void setKohezgjatja(String kohezgjatja) {
	this.kohezgjatja = kohezgjatja;
}



}
