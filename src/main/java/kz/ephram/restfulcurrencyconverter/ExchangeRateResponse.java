package kz.ephram.restfulcurrencyconverter;

import java.util.Map;

//POJO класс для получения данных
public class ExchangeRateResponse {
	private boolean success;
	private long timestamp;
	private String base;
	private String date;

	private Map<String, Double> rates;

	// Getters and setters

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "ExchangeRateResponse{" + "success=" + success + ", timestamp=" + timestamp + ", base='" + base + '\''
				+ ", date='" + date + '\'' + ", rates=" + rates + '}';
	}
}
