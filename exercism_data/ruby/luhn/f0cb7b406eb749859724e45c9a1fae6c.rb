class Luhn

	def self.create(number)
		digit = (10 - (new(number*10).checksum % 10)) % 10
		(number * 10) + digit
	end

	def initialize(number)
		@digits = number.to_s.chars.map(&:to_i)
	end
	
	def addends
		@digits.reverse.map.with_index do |number, idx|
			idx.odd? ? convert_digit(number) : number
		end.reverse
	end
	
	def checksum
		addends.reduce(:+)
	end
	
	def valid?
		checksum.modulo(10) == 0
	end
	
private
	def convert_digit(digit)
		result = digit * 2
		result > 10 ? result - 9 : result
	end
end
