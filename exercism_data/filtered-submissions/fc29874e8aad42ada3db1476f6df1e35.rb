class Hamming

	def compute(x, y)
		x.chars.zip(y.chars).inject(0) {|distance, pair| pair[0] == pair[1] ? distance : distance + 1}
	end
end
