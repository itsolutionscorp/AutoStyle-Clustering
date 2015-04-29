class Integer

	ROMAN_NUMERALS = {
		1 => "I",
		4 => "IV", 
		5 => "V", 
		9 => "IX", 
		10 => "X", 
		40 => "XL", 
		50 => "L", 
		90 => "XC", 
		100 => "C", 
		400 => "CD",
		500 => "D", 
		900 => "CM", 
		1000 => "M"
	}

	def to_roman
		input = self
		stack = []
		romanString = ""
		
		while input > 0
			ROMAN_NUMERALS.each do |arabic_numeral, roman_numeral|
				if arabic_numeral <= input
					stack.push(arabic_numeral)
				end
			end
			romanString << ROMAN_NUMERALS[stack.max].to_s
			input = input - stack.pop
			stack = []
		end
		
		romanString
	end
	
end
