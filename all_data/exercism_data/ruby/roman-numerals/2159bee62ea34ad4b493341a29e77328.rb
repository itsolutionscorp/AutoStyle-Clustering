class Fixnum


	def to_roman
		str = ''
		a_number = to_i
	

		(1..4).each { |i| str.prepend( arabic_to_roman(a_number, 10**i) ) }
		str

	end

	def arabic_to_roman(arabic, decade)	
		decade_str =''
		case decade 
		when 10
			one = 'I'
			five = 'V'
			ten = 'X'
		when 100
			one = 'X'
			five = 'L'
			ten = 'C'
		when 1000
			one = 'C'
			five = 'D'
			ten = 'M'
		when 10000
			one = 'M'
		end

		digit = (arabic % decade ) / (decade / 10)
		case digit
		when 1, 2, 3
			decade_str = one  * digit
		when 4
			decade_str = one + five
		when 5
			decade_str = five
		when 6, 7, 8
			decade_str = five + one * (digit - 5)
		when 9
			decade_str = one + ten
		end

		decade_str
	end
end
