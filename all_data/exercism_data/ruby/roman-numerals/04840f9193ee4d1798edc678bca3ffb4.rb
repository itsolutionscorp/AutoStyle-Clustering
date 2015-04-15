#!/usr/bin/env ruby

class Integer
	def to_roman
		#retval = ""
		#case self
		#when 1
		#	retval = 'I'
		#end
		
		#retval
		Roman.new(self).convert
	end
end

class Roman
	def initialize(intinput)
		@intinput = intinput
	end

	ROMAN_NUMERAL = {
	'M' => [1000, 900, 'CM'],
	'D' => [500, 400, 'CD'],
	'C' => [100, 90, 'XC'],
	'L' => [50, 40, 'XL'],
	'X' => [10, 9, 'IX'],
	'V' => [5, 4, 'IV'],
	'I' => [1, 0, '']
	}

	def num_to_str(numeral,rem_arabic,rom_str)

		value = ROMAN_NUMERAL[numeral][0]
		thresh_val = ROMAN_NUMERAL[numeral][1]
		thresh_str = ROMAN_NUMERAL[numeral][2]

		
		rom_str << numeral*(rem_arabic/value)
		rem_arabic = rem_arabic % value
		if (rem_arabic > (thresh_val - 1))
			rom_str << thresh_str
			rem_arabic = rem_arabic - thresh_val
		end

		return rem_arabic, rom_str
	end

	def convert
		arabic_int = @intinput
		ret_string = String.new

		ROMAN_NUMERAL.each_key do |key|
			arabic_int, ret_string = num_to_str(key, arabic_int, ret_string)
			#puts key
			#puts arabic_int
			#puts ret_string
		end
		
		return ret_string
	end
end
