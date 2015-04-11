class Hamming
	def self.compute(dna_strand_a, dna_strand_b)
		hamming_distance = 0
		[dna_strand_a.length, dna_strand_b.length].min.times do |index|
			hamming_distance += 1 unless dna_strand_a[index] == dna_strand_b[index]
		end
		hamming_distance
	end
end
