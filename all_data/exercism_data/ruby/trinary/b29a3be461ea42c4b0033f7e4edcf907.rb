class Trinary
	def initialize(value)
		@value = valid?(value) ? value : "0"
	end
	
	def to_decimal
		@value.reverse.each_char.with_index.inject(0) do |sum, (digit, idx)|
			sum + digit.to_i * (3 ** idx)
		end
	end

private
	def valid?(value)
		value.scan(/[^0-2]/).empty?
	end
end
