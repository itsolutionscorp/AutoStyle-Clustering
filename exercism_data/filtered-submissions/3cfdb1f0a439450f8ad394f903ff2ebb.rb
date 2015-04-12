class Hamming
	def compute(seq1, seq2)
		h_dist = 0
		num_elm = [seq1.size, seq2.size].min
		for elm in 0..num_elm-1
			h_dist += 1 if seq1[elm] != seq2[elm]
		end
		h_dist
	end
end
