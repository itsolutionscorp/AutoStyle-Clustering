class Integer

	def units_place(number)
		units = [nil,"I","II","III","IV","V","VI","VII","VIII","IX"]
		number = number % 10
    	return units[number]
	end

	def tens_place(number)
		tens = [nil,"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"]
		return tens[(number%100)/10]
	end

	def hundreds_place(number)
		hundreds = [nil,"C","CC","CCC","CD","D","DC","DCC","DCCC","CM"]
		return hundreds[(number%1000)/100]
	end

	def thousands_place(number)
		thousands = [nil,"M","MM","MMM"]
		return thousands[(number/1000)]
	end

	def to_roman
		"#{thousands_place(self)}#{hundreds_place(self)}#{tens_place(self)}#{units_place(self)}"
	end
end
