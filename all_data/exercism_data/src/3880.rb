def compute(seq1,seq2)
		result = 0
		length = [seq1.length,seq2.length].min - 1
		(0..length).each { |index| result += 1 if seq1[index] != seq2[index] }
		result
	end