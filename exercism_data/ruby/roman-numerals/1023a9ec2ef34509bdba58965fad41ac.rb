class Fixnum
	NUMERALS = {
		1000 => 'M',
		999 => 'IM',
		990 => 'XM',
		900 => 'CM',
		500 => 'D',
		499 => 'ID',
		490 => 'XD',
		400 => 'CD',
		100 => 'C',
		99 => 'IC',
		90 => 'XC',
		50 => 'L',
		49 => 'IL',
		40 => 'XL',
		10 => 'X',
		9 => 'IX',
		5 => 'V',
		4 => 'IV',
		1 => 'I'
	}

	def to_roman
		temp = self
		str = ''
	
		while temp > 0
			NUMERALS.each do |value, letter| 
				if temp >= value
					str += letter
					temp -= value
					break
				end
			end
		end

		str
	end
end
