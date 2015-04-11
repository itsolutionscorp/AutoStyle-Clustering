class Hamming
	def self.compute(strand_a, strand_b)
		hamming_difference = 0
		
		strand_a.length.times do |position|
			if strand_a[position] != strand_b[position]
				hamming_difference += 1
			end
		end
		hamming_difference
	end
end
