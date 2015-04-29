HEX_CHARS = Hash[[*('0'..'9'), *('a'..'f')].zip([*(0..15)])]

class Hexadecimal
	def initialize(hex)
		@hex = (hex.downcase =~ /[^0-9a-f]/) ? '' : hex.downcase
	end

	def to_decimal()
		decimal = 0

		@hex.reverse.each_char.with_index do |c, i| 
			decimal += HEX_CHARS[c] * 16 ** i
		end

		decimal
	end
end
