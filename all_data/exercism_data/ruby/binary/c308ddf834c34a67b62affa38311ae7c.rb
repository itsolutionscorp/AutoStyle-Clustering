class Binary
	attr_reader:to_decimal
	def initialize(bin)
		@to_decimal = 0
		bin.split('').reverse.each_with_index do |b,i|
			break if !b.match(/[01]/)
			@to_decimal += b.to_i * 2**i
		end
	end
end
