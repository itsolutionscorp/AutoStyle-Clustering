module Roman

	#CONVERSIONS = { '1' => 'I', '5' => 'V', '10' => 'X', '50' => 'L', '100' => 'C', '500' => 'D' => , '1000' => 'M' }

	CONVERSIONS = [ [1000, "M"], [900, 'CM'], [500, "D"], [400, 'CD'], [100, 'C'], 
		[90, 'XC'], [50, "L"], [40, "XL"], [10, "X"], 
		[9, "IX"], [5, "V"], [4, "IV"], [1, "I"] ]

	def to_roman
		i = 0
		result = ""
		rest = self
		while rest > 0
			if rest >= CONVERSIONS[ i ][ 0 ]
				result += CONVERSIONS[ i ][ 1 ]
				rest -= CONVERSIONS[ i ][ 0 ]
			else
				i += 1
			end
		end
		result
	end

end

class Fixnum
	include Roman
end
