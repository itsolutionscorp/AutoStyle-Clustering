class Binary
	def initialize(bin)
		@bin = bin
	end

	def to_decimal
		return 0 if @bin =~ /[^01]/
		@bin.reverse.chars.each_with_index.reduce(0){|sum, (c,i)| sum + ((c=='1') ? 2**i : 0)}
	end
end
