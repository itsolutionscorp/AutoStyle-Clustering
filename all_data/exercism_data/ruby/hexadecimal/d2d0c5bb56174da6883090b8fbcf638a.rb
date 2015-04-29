class Hexadecimal
	HEX = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"]
	def initialize(string)
		@string = string.upcase
	end
	def to_decimal
		return 0 if @string.match(/[^A-F0-9]/)
		array = @string.split(//).reverse
		array.each_with_index.map do |x,i|
			HEX.index(x).to_i*16**i
		end.reduce(0){|sum,e|sum + e}
	end
end
