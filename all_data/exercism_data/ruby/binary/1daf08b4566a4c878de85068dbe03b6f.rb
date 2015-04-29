class Binary
	def initialize(bin)
		@bin = bin
	end

	def to_decimal
		return 0 if @bin =~ /\D/
		sum = 0
		@bin.chars.map(&:to_i).reverse.each_with_index { |x, i| sum += x*(2**i) }
		sum
	end
end
