class Binary
	def initialize(n)
		@binary = n
	end
	def to_decimal
		b = @binary.to_s.split(//).collect{|s| s.to_i}.reverse #reverse array
		if @binary =~ /\A\d+\z/
			b.map.with_index{|e,i| e*(2**i)}.inject{|sum, x| sum + x}
		else
			0
		end
	end
end
