class Binary

	def initialize(input)
		@binary = sanitize_input(input)
	end

	def sanitize_input(input)
		found = input.match /[^01]+/
		if found
			return 0
		else
			return input
		end
	end

	def to_decimal
		sum = 0
		@binary.to_s.reverse.split("").each_with_index do |i, index|
			sum += i.to_i*(2**index)
		end
		return sum
	end

end
