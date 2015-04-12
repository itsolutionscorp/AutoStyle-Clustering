class Hamming
	def compute(dna_strand_a, dna_strand_b)
		hamming_distance = 0
		min_length = [dna_strand_a.length, dna_strand_b.length].min

		min_length.times do |i|
			hamming_distance += 1 unless dna_strand_a[i] == dna_strand_b[i]
		end

		hamming_distance
	end
end
