class Fixnum

	ROMAN_MAPPING = {
		1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C",
		90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV",
		1 => "I"
	}

	def to_roman
		ret_roman = ""
		passed_number = self
		ROMAN_MAPPING.each_pair do |arabic, roman|
			while passed_number >= arabic
				passed_number -= arabic
				ret_roman << roman
			end
		end
		ret_roman
	end
end
