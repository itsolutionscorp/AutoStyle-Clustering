class Hexadecimal
	def initialize(hex_string)
		@hex_string = valid?(hex_string) ? hex_string : "0"
	end
	
	def to_decimal
		@hex_string.reverse.each_char.with_index.inject(0) do |sum, (digit, index)|
			sum += dec_digit(digit) * (16 ** index)
		end
	end
	
private
	def dec_digit(hex_digit)
		if hex_digit.match(/[0-9]/)
			hex_digit.to_i
		else
			10 + (hex_digit.upcase.ord - 'A'.ord)
		end
	end
	
	def valid?(char)
		char.upcase.match(/[^0-9A-F]/).nil?
	end
end
