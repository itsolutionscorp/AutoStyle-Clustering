class Binary
	attr_reader :binary

	def initialize(binary)
		@binary = binary
	end

	def to_decimal
		if binary.match(/[a-zA-Z2-9]/)
			decimal = 0
		else
			b_numbers = binary.chars.reverse { |d| d.to_i }

			decimal = b_numbers.each_with_index.reduce(0) do |sum, (value, index)|
				sum + value.to_i * (2 ** index)
			end
		end
  end
end
