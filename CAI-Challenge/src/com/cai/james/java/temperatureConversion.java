package com.cai.james.java;

public class temperatureConversion {

	double CelsiusToFahrenheit(double celsius) {
		return((9*celsius)/5)+32;
	}
	
	double CelsiusToKelvin(double celsius) {
		return (celsius + 273.15);
	}
	
	double FahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit -32) *5/9;
	}
	
	double FahrenheitToKelvin(double fahrenheit) {
		return ((fahrenheit - 32) * 5 / 9) + 273.15; 
	}
	
	double KelvinToFahrenheit(double kelvin) {
		return (((kelvin - 273.15) * 9) / 5) + 32;
	}
	
	double KelvinToCelsius(double kelvin) {
		return (kelvin - 273.15);
	}
}
