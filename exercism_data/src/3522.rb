class Hamming
	def compute(strand_a,strand_b)
		hamming_distance = 0
		 (0...strand_a.length).count do |nucleotide_position|
			hamming_distance += 1 if strand_a[nucleotide_position] != strand_b[nucleotide_position]
		end
		hamming_distance
	end
end
