package oplossing;

public class AReservatie {
	private int resId;
	private Integer voertuigId;	//Wanneer NULL -> niet gelukt
	private int penalty1;
	private int penalty2;
	private int gewZoneId; //De gewenste zone
	AVoertuig voertuig;
	
	public AVoertuig getVoertuig() {
		return voertuig;
	}
	public void setVoertuig(AVoertuig voertuig) {
		this.voertuig = voertuig;
	}
	public int getGewZoneId() {
		return gewZoneId;
	}
	public void setGewZoneId(int gewZoneId) {
		this.gewZoneId = gewZoneId;
	}
	public int getPenalty1() {
		return penalty1;
	}
	public void setPenalty1(int penalty1) {
		this.penalty1 = penalty1;
	}
	public int getPenalty2() {
		return penalty2;
	}
	public void setPenalty2(int penalty2) {
		this.penalty2 = penalty2;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public Integer getVoertuigId() {
		return voertuigId;
	}
	public void setVoertuigId(Integer voertuigId) {
		this.voertuigId = voertuigId;
	}
}
