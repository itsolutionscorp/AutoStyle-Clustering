class Binary
	def initialize(bin_number)
		@bin = bin_number
	end

	def to_decimal
		@bin.chars.each_index.reduce(0) do |acc, k|
			return 0 if (!"01".include? @bin[k])
			acc += @bin[k].to_i * (2 ** (@bin.length - k - 1))
		end
	end
end
