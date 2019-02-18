package oplossing;

public class AReservatie {
	private int resId;
	private Integer voertuigId;	//Wanneer NULL -> niet gelukt
	
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
