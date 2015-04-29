class Trinary
	def initialize(value)
		@value = value
	end
	
	def to_decimal
		sum = 0
		@value.reverse.each_char.with_index do |digit, idx|
			sum += digit.to_i * (3 ** idx)
		end if valid?
		sum
	end

private
	def valid?
		@value.scan(/[^0-2]/).empty?
	end
end
