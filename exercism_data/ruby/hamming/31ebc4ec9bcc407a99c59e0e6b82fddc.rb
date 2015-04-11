class Hamming

	def self.compute(dna1, dna2)
		nbr_times = dna1.size
		nbr_times = dna2.size if dna2.size < dna1.size
		
		difference = 0
		nbr_times.times do |i|
			difference += 1 if dna1[i] != dna2[i]
		end
		difference
	end

end
