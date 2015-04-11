class Binary
	# Binary.new("1").to_decimal
	def initialize bstr
		@bstr = bstr
	end

	def to_decimal
		@bstr.reverse.split('').each_with_index.map { |digit, index|
			(Integer digit) * 2 ** index
		}.inject(&:+)
	rescue 
		0
	end
end
