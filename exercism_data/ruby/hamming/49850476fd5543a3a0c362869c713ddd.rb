class Hamming

	def self.compute(strand_one, strand_two)
		hamming = 0
		for i in 0...strand_one.length
			hamming += 1 if (strand_two[i] && strand_one[i] != strand_two[i])
		end
		hamming
	end
end
