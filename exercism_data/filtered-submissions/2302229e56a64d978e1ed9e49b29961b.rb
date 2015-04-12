def compute(dna_sequence_1, dna_sequence_2)
		hamming_distance = 0

		min_sequence = ["#{dna_sequence_1}".length, "#{dna_sequence_2}".length].min
		hamming_distance = min_sequence.times.count { | i | dna_sequence_1[i] != dna_sequence_2[i]}
	end