class Hamming

	def compute(strand1, strand2)
		strand1.chars.each_with_index.inject(0) do |dist, (nuc1, i)|
			nuc1 != strand2[i] ? dist += 1 : dist
		end
	end

end
