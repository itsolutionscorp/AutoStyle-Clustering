class Hamming
	def compute(strand1, strand2)
		min_length = [strand1.size, strand2.size].min
		full_array = (strand1[0...min_length].chars).zip(strand2[0...min_length].chars)
		full_array.count { |pair| pair[0] != pair[1] }
	end
end
