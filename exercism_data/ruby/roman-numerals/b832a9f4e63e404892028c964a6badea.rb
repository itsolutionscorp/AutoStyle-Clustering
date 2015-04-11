class Fixnum

	#converts a number to its roman numeral represenation, don't go above 3000
	def to_roman
			num_map = { 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}
			to_print = ""
			mod = self
			num_map.each do | numVal, romanChars |
				while mod >= numVal
					mod -= numVal
					to_print << romanChars
				end
			end
	to_print
	end
end
