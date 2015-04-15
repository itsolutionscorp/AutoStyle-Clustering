module Numeral
	def to_roman
		Numeral.roman(self)
	end
	
	module_function

	ARABIC = [ 1,  4,   5,  9,  10,  40, 50, 90, 100, 400, 500, 900,1000].reverse
	ROMAN =  ['I','IV','V','IX','X','XL','L','XC','C','CD','D','CM','M'].reverse
	NUMERAL = Hash[ARABIC.zip(ROMAN)]
	
	def roman(number)
		numeral = ''
		NUMERAL.each do |value, symbol|
			while number >= value
				numeral << symbol
				number -= value
			end
		end
		numeral
	end
end

class Fixnum
	include Numeral
end
