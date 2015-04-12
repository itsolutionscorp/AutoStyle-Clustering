class Hamming
	def compute(dna_strand_a, dna_strand_b)
		distance = 0

		[dna_strand_a.length, dna_strand_b.length].min.times do |index|
			distance += 1 unless dna_strand_a[index] == dna_strand_b[index]
		end

		distance
	end
end
