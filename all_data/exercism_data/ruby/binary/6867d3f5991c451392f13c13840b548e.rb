class Binary

	attr_reader :binary
	
	def initialize(binary)
		@binary = binary
	end
	
	def to_decimal
		return 0 if /[^01]/.match(binary)
		decimal = 0
		binary.reverse.split(//).each_with_index do |b, i|
			decimal += b.to_i * 2 ** i
		end
		decimal
	end

end
