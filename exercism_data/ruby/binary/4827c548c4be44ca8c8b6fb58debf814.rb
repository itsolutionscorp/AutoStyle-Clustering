class Binary
	attr_reader :number, :binaries
	def initialize(number)
		@number = number
		@binaries = extract_binaries @number
	end

	def to_decimal
		revert_binaries_order
		decimal = 0
		@binaries.each_with_index do |number, index|
			decimal = decimal + 2 ** index if number == 1
		end
		return decimal
	end

	def revert_binaries_order
		@binaries.reverse!
	end

	def extract_binaries(number)
		number.split(//).collect { |i| i.to_i }
	end
end
