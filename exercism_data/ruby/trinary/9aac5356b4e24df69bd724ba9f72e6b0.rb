class Trinary
	def initialize(string)
		@string = string
	end

	def to_decimal
		return number = 0 if @string.match(/[^0-9]/)
		array = @string.split(//).reverse.map.with_index{ |x,i| x.to_i*(3**i)}
		number = array.inject{ |sum, n| sum + n }
	end
end
