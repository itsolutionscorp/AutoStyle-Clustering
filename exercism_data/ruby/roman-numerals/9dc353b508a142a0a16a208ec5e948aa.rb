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
		numerals = NUMERALS.clone
	
		while temp > 0
			x = numerals.shift
			while temp >= x[0]
				str += x[1]
				temp -= x[0]		
			end
		end

		str
	end
end

# http://exercism.io/submissions/63ab096de820d799b0245019
