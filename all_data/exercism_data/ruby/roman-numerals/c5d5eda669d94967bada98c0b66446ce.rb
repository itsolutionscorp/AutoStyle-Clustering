module Roman

	NUMERALS = {
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
		original_number = self
		roman_number = ""
		NUMERALS.sort_by { |value, numeral| value }
		NUMERALS.each_pair do |value, numeral|
			while original_number >= value
				original_number -= value
				roman_number << numeral
			end
		end
		roman_number
	end
end



class Integer
	include Roman
end
