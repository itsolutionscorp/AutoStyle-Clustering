class Integer	
	def to_roman
		number = self
	  roman_numeral_finished = ""
	  roman_values =  {
						1000 => "M", 
						900 => "CM", 
						500 => "D",
						400 => "CD", 
						100 => "C", 
						90 => "XC",
						50 => "L", 
						40 => "XL", 
						10 => "X",
						9 =>"IX",
						5 => "V", 
						4 => "IV", 
						1 => "I"
					}

	  roman_values.each do |base, letter|
	    roman_numeral_finished << letter * (number/base)
	    number %= base
	  end
	  roman_numeral_finished
	end
end

25.to_roman
