class Binary

	attr_reader :to_decimal
	
	def initialize(binary_string)
		@to_decimal = 0
		if valid?(binary_string)
			binary_string.split('').reverse.each_with_index do |unit, index|
				@to_decimal += unit.to_i * (2**index)
			end
		end
	end

	def valid?(input_string)
		input_string =~ /\A[10]+\Z/
	end
end
