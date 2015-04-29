class Fixnum

	def to_roman
		result = ""
		number = self
		roman_numerals = {
			"M" => 1000,
			"CM" => 900,
			"D" => 500,
			"CD" => 400,
			"C" => 100,
			"XC" => 90,
			"L" => 50,
			"XL" => 40,
			"X" => 10,
			"IX" => 9,
			"V" => 5,
			"IV" => 4,
			"I" => 1	
		}
		while number != 0
			roman_numerals.each do |roman, arabic|
				while (number / arabic) > 0
					number = number - arabic
					result += roman
				end
			end
		end
		result
	end
	
end
