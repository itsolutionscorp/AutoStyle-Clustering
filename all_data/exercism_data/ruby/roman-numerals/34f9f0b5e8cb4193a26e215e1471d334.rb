class Fixnum 	

def to_roman
	def to_roman_helper(roman, number)
		if number == 0
			roman
		else
			k, v = roman_mapping.find { |k,v| number >= k }
			to_roman_helper(roman + v, number - k)
		end
	end

	to_roman_helper('', self)

end

private

	def roman_mapping 
		{
			1000 => 'M',
			900 => 'CM',
			500 => 'D',
			400 => 'CD',
			100 => 'C',
			90 => 'XC',
			50 => 'L',
			40 => 'XL',
			10 => 'X',
			9 => 'IX',
			5 => 'V',
			4 => 'IV',
			1 => 'I'
		}
	end
end
