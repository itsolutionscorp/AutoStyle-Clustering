class Binary
	def initialize(value)
		@decimal_value = binary_to_decimal(value)
	end
	
	def to_decimal
		@decimal_value
	end
	
private
	def binary_to_decimal(binary_string)
		sum = 0
		if valid_binary?(binary_string)
			binary_string.chars.reverse.each_with_index do |bit, index|
				sum += index > 0 ? ((2 * bit.to_i) ** index) : bit.to_i
			end
		end
		sum
	end
	
	def valid_binary?(string)
		string.scan(/[^0|1]/).empty?
	end
end
