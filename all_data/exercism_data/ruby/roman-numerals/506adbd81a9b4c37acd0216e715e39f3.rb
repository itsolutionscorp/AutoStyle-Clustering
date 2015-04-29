class Fixnum

	def to_roman
		roman_number = ''
		number = self
		#M
			m = number.divmod(1000)[0]
			m.times {roman_number += 'M'}
			number = number.divmod(1000)[1]
		#CM
		if number >= 900
			roman_number += 'CM'
			number = number - 900
		end
		#D
			d = number.divmod(500)[0]
			d.times {roman_number += 'D'}
			number = number.divmod(500)[1]
		#CD
		if number >= 400
			roman_number += 'CD'
			number = number - 400
		end
		#C
			c = number.divmod(100)[0]
			c.times {roman_number += 'C'}
			number = number.divmod(100)[1]
		#XC
		if number >= 90
			roman_number += 'XC'
			number = number - 90
		end
		#L
			l = number.divmod(50)[0]
			l.times {roman_number += 'L'}
			number = number.divmod(50)[1]
		#XL
		if number >= 40
			roman_number += 'XL'
			number = number - 40
		end
		#X
			x = number.divmod(10)[0]
			x.times {roman_number += 'X'}
			number = number.divmod(10)[1]
		#IX
		if number >= 9
			roman_number += 'IX'
			number = number - 9
		end
		#V
			v = number.divmod(5)[0]
			v.times {roman_number += 'V'}
			number = number.divmod(5)[1]
		#IV
		if number >= 4
			roman_number += 'IV'
			number = number - 4
		end
		#I
			i = number
			i.times {roman_number += 'I'}		
		roman_number
	end
	
end
