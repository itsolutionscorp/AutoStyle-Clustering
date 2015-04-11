class Trinary
	def initialize(str_val)
		@str_val = str_val
	end

	def to_decimal
		if @dec_val.nil?
			@dec_val = 0
			@str_val.split('').reverse.each_with_index do |digit, index|
				if ('0'..'9') === digit
					@dec_val += digit.to_i * (3**index)
				else
					@dec_val = 0
					break
				end
			end
		end
		@dec_val
	end
end
