class Integer

	def to_roman
		number = self
		roman_number = ""
		
		roman_numerals.keys.each do |divisor|
			quotient, remainder = number.divmod(divisor)
			roman_number << roman_numerals[divisor] * quotient
			number = remainder
		end
		roman_number
	end

	private
	
	def roman_numerals
    {
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
	end
end
