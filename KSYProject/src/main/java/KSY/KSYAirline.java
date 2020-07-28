package KSY;

import org.apache.hadoop.io.Text;

public class KSYAirline {
	private int ActualElapsedTime; //12번째
	private String uniqueCarrier;
	private boolean timeAvailable = true;
	public KSYAirline(Text text) {
		try {
			//csv 파일: ,로 데이터 구분
			String[] columns = text.toString().split(",");
			uniqueCarrier = columns[8];
			if(!columns[18].equals("NA")) {
				ActualElapsedTime = Integer.parseInt(columns[12]);
			}else {
				timeAvailable = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//getter
	public int getActualElapsedTime() {
		return ActualElapsedTime;
	}
	public String getUniqueCarrier() {
		return uniqueCarrier;
	}
	public boolean isTimeAvailable() {
		return timeAvailable;
	}
	
}
