class Hamming

	def compute(dna1, dna2)
		nbr_times = [dna1.size, dna2.size].min
		difference = 0
		nbr_times.times do |i|
			difference += 1 if dna1[i] != dna2[i]
		end
		difference
	end

end
