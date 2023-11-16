package kz.ephram.restfulcurrencyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConverterController {

	/*
	 * метод в котором выполняется запрос на сторонний сервис, из него получаем наши
	 * валюты и их курс к отношению ЕВРО, также получаем количество валюты к обмену.
	 * http://api.exchangeratesapi.io/v1/latest?access_key=
	 * 22c4fb6ef123c36b278731d0757d2438&symbols=USD,AUD,CAD,PLN,MXN&format=1 пример
	 * запроса в браузере такой -
	 * http://localhost:8080/currency?from=CAD&to=USD&amount=100
	 */
	@RequestMapping("/currency")
	public String getCurrencyExchange(@RequestParam String from, @RequestParam String to, @RequestParam String amount) {
		
		//добавил try блок для вылавливания ошибок
		try {

			// REST URL стороннего сервиса
			String url = "http://api.exchangeratesapi.io/v1/latest?access_key=22c4fb6ef123c36b278731d0757d2438&symbols="
					+ from + "," + to;

			// создаем RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// делаем запрос GET на сторонний сервис и парсим его в обьект
			// ExchangeRateResponse
			ExchangeRateResponse exchangeRateResponse = restTemplate.getForObject(url, ExchangeRateResponse.class);

			// получаем map пару Валюта:курс к отношонию к Евро
			Map<String, Double> ratesMap = exchangeRateResponse.getRates();

			// получаем курсы валют в виде BigDecimal
			BigDecimal number1 = new BigDecimal(ratesMap.get(from));
			BigDecimal number2 = new BigDecimal(ratesMap.get(to));

			// вторую валюту умножаем на количество amount
			number2 = number2.multiply(new BigDecimal(amount));

			// разделяем на первую валюту, округлением до 10 позиций после запятой
			BigDecimal result = number2.divide(number1, 10, RoundingMode.HALF_UP);

			// возврат результата в виде строки
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			//вывод в браузере в случае ввода неправильного значения
			return "Неправильно введены значения";
		}
	}
}
