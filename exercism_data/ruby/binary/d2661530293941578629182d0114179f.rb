class Binary
	def initialize(bin_number)
		@bin = bin_number
	end

	def to_decimal
		len = @bin.length
		@bin.chars.reduce(0) do |acc, v|
			return 0 if (!"01".include? v)
			len -= 1
			acc += v.to_i * (2 ** len)
		end
	end
end
