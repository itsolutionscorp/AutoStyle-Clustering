ROMAN_NUMERALS = {
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

class Integer
	def to_roman
		num = self
		roman_string = ""
		ROMAN_NUMERALS.each do |x, y|
			while num >= x
				num -= x
				roman_string << y
			end
		end
		roman_string
	end
end
