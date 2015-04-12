class Hamming

	# Takes 2 DNA sequences and returns the
	# computed Hamming distance
	def compute(seq1, seq2)
		count = [seq1.length, seq2.length].min
		hamming_distance = 0
		
		for i in 0...count
			if seq1[i] != seq2[i]
				hamming_distance += 1
			end
		end

		return hamming_distance
	end
end
