class Binary
	attr_reader :binary
	def initialize ( binary_string )
		if binary_string.match( /^[0-1]+$/ )
			@binary = binary_string.split("").map(&:to_i)
		else
			@binary = [0]
		end
	end

	def to_decimal
		decimal = 0
		@binary.reverse.each_with_index do |present, power|
			decimal += present*2**power
		end
		decimal
	end
end
