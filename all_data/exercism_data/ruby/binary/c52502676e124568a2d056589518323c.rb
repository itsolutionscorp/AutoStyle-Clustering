class Binary
	attr_reader :to_decimal
	def initialize(bin_string)
		@to_decimal = converter(bin_string)
	end
	def converter(conv_string)
		if conv_string.length > 0 && !conv_string.match(/[^01]/) then
			conv_string.chars.first.to_i * 2**(conv_string.length - 1) + converter(conv_string.slice(1,conv_string.length))
		else
			0
		end
	end
end
