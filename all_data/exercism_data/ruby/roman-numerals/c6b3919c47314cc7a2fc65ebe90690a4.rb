class Fixnum
	def to_roman
		num = self

		ROMANS.each_with_object("") do |(factor, roman_char), roman|
			n, num = num.divmod(factor)
			roman << roman_char * n
		end

	end

	ROMANS = [
		
		[1000,'M'],
		[900, 'CM'],
		[500, 'D'],
		[400, 'CD'],
		[100, 'C'],
		[90, 'XC'],
		[50, 'L'],
		[40, 'XL'],
		[10, 'X'],
		[9, 'IX'],
		[5, 'V'],
		[4, 'IV'],
		[1, 'I']
	]

end
