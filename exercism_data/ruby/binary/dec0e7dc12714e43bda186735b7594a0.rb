class Binary
	def initialize(string)
		return @number = 0 if string.match(/[^0-9]/)
		array = string.split(//).reverse.map.with_index do |x, i| 
			x.to_i*(2**i)
		end
		@number = array.inject { |sum, n| sum + n }
	end
	def to_decimal
		@number
	end
end
