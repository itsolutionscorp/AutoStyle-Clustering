class Trinary

	def initialize(input)
		@number = sanitize_input(input)
	end

	def sanitize_input(input)
		if input =~ /[^0-9]/
			return "0"
		else
			return input
		end
	end

	def to_decimal
		sum = 0
		@number.reverse.split("").each_with_index do |i, index|
			sum += i.to_i*(3**index)
		end
		sum
	end
end
