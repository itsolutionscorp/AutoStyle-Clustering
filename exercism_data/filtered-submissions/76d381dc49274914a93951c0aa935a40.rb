class Hamming
	def compute(dna1, dna2)
		hamming_distance = 0
		dna1.size.times { |i| hamming_distance += 1 if (dna1[i] != dna2[i]) }
		hamming_distance
	end
end
