class Trinary
	def initialize(value)
		@value = value
	end

	def to_decimal
		# this is the easy way
		#@value.to_i(3)

		# this is the hard way
		num = @value.to_i
		i = 0
		result = 0
		while num > 0
			num, digit = num.divmod(10)
			result += digit * 3**i
			i += 1
		end

		result
	end
end
