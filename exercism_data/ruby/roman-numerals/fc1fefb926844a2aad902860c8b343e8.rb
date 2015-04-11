class Fixnum

	TRANSLATE = {
		1000 => "M",
		 900 => "CM",
		 500 => "D",
		 400 => "CD",
	   	 100 => "C",
	 	  90 => "XC",
		  50 => "L",
		  40 => "XL",
		  10 => "X",
		   9 => "IX",
		   5 => "V",
		   4 => "IV",
		   1 => "I"
	}

	def to_roman
		numb_left = self
		roman_numeral = ''
		TRANSLATE.each do |(numb, letter)|
			while numb_left >= numb
				roman_numeral << letter
				numb_left -= numb
			end
		end
		roman_numeral
	end

end
