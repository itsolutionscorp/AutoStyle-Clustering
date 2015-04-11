class Fixnum
	def to_roman
		int_to_roman = { 1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD', 
			100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X', 9 => 'IX', 
			5 => 'V', 4 => 'IV', 1 => 'I' }

		remainder = self
		roman_string = ""
		while remainder > 0
			int_to_roman.each do |int, roman|
				if remainder >= int
					roman_string += roman
					remainder -= int
					break
				end
			end
		end

		roman_string
	end
end
