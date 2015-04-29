class Integer

	ARABIC_TO_ROMAN_MAP = {
	1 => 'I',
	4 => 'IV',
	5 => 'V',
	9 => 'IX',
	10 => 'X',
	40 => 'XL',
	50 => 'L',
	90 => 'XC',
	100 => 'C',
	400 => 'CD',
	500 => 'D',
	900 => 'CM',
	1000 => 'M'
	}.sort_by{|a,r|-a}

	

	def to_roman
		
		roman_numeral = String.new
		remainder = self

		ARABIC_TO_ROMAN_MAP.each {|a,r|
	
			times_divisible = remainder / a
			if times_divisible > 0
				remainder -= times_divisible * a
				roman_numeral.concat(r*times_divisible)
			else
				roman_numeral
			end
		}

		roman_numeral
				
	end
end


puts 1990.to_roman #=> MCMXC
