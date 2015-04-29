class Binary
	attr_reader :binary
	def initialize(binary)
		@binary = binary
	end
	def to_decimal
		decimal = 0
		bits = binary.chars.map(&:to_i)
		power = bits.length-1
		invalid = binary.to_i.to_s != binary
		unless invalid
			bits.each do |bit|
				decimal += bit*(2**power)
				power-=1
			end
		end
		decimal
	end
end
