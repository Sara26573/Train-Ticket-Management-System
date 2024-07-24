package application;

import java.time.LocalDateTime;

public class KlientaData {
	private String emer;
	private String mbiemer;
	private String numerTelefoni;
	private String NumerKrediti;
	private String daatSkadences;
	private int cvv;
	private String burimi;
	private String destinacioni;
	private int vende;
	private float cmimi;
	private String idNumber;
	private LocalDateTime dataeRezervimit;
	private LocalDateTime dataeNisjes;
	private int id;
	
	public KlientaData(String emer, String mbiemer, String numerTelefoni, String numerKrediti, String daatSkadences,
			int cvv, String burimi, String destinacioni, int vende, float cmimi, String idNumber,
			LocalDateTime dataeRezervimit, LocalDateTime dataeNisjes, int id) {
		super();
		this.emer = emer;
		this.mbiemer = mbiemer;
		this.numerTelefoni = numerTelefoni;
		NumerKrediti = numerKrediti;
		this.daatSkadences = daatSkadences;
		this.cvv = cvv;
		this.burimi = burimi;
		this.destinacioni = destinacioni;
		this.vende = vende;
		this.cmimi = cmimi;
		this.idNumber = idNumber;
		this.dataeRezervimit = dataeRezervimit;
		this.dataeNisjes = dataeNisjes;
		this.id = id;
	}
	public String getEmer() {
		return emer;
	}
	public void setEmer(String emer) {
		this.emer = emer;
	}
	public String getMbiemer() {
		return mbiemer;
	}
	public void setMbiemer(String mbiemer) {
		this.mbiemer = mbiemer;
	}
	public String getNumerTelefoni() {
		return numerTelefoni;
	}
	public void setNumerTelefoni(String numerTelefoni) {
		this.numerTelefoni = numerTelefoni;
	}
	public String getNumerKrediti() {
		return NumerKrediti;
	}
	public void setNumerKrediti(String numerKrediti) {
		NumerKrediti = numerKrediti;
	}
	public String getDaatSkadences() {
		return daatSkadences;
	}
	public void setDaatSkadences(String daatSkadences) {
		this.daatSkadences = daatSkadences;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
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
	public float getCmimi() {
		return cmimi;
	}
	public void setCmimi(float cmimi) {
		this.cmimi = cmimi;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public LocalDateTime getDataeRezervimit() {
		return dataeRezervimit;
	}
	public void setDataeRezervimit(LocalDateTime dataeRezervimit) {
		this.dataeRezervimit = dataeRezervimit;
	}
	public LocalDateTime getDataeNisjes() {
		return dataeNisjes;
	}
	public void setDataeNisjes(LocalDateTime dataeNisjes) {
		this.dataeNisjes = dataeNisjes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
