def compute(strand_a,strand_b)
		nucleotide_position = 0
		hamming_distance = 0
		while nucleotide_position < strand_a.length do
			hamming_distance += 1 if strand_a[nucleotide_position] != strand_b[nucleotide_position]
			nucleotide_position += 1
		end
		hamming_distance
	end