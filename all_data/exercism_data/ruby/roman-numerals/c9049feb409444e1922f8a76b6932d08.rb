module RomanNumerals
	# Public: Returns the Roman Numeral notation for the supplied Fixnum
	#
	# Conversion methodolgy:
	# Roman symbols are placed from left to right in order of value, 
	# starting with the largest. 
	# 
	# However, in a few specific cases, to avoid four characters being repeated
	# in succession (such as IIII or XXXX) these can be reduced using 
	# subtractive notation as follows:
	#
	#     * the numeral I can be placed before V and X 
	#       to make 4 units (IV) and 9 units (IX) respectively
	# 
	#     * X can be placed before L and C to make 40 (XL) and 90 (XC) 
	# 
	#     * C can be placed before D and M to make 400 (CD) and 900 (CM) 

	NUMERALS = {
		1 => 'I',
		5 => 'V',
		10 => 'X',
		50 => 'L',
		100 => 'C',
		500 => 'D',
		1000 => 'M'
	}

	NOTES = {
		4 => 'IV',
		9 => 'IX',
		40 => 'XL',
		90 => 'XC',
		400 => 'CD',
		900 => "CM"
	}

	ROMAN = NUMERALS.merge(NOTES)

	ROMAN_NUMBERS = ROMAN.keys.sort.reverse


	def to_roman
		# take care of the simple numbers
		return ROMAN[self] if ROMAN[self]

		num = self
		str = ''

		ROMAN_NUMBERS.each do |rn|
			qr = num.divmod(rn)
			str << ROMAN[rn] * qr[0]
			num = qr[1]
		end
		str
	end
end

class Fixnum
	include RomanNumerals
end
