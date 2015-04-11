class Hamming
	def self.compute (dna1, dna2)
		hamming = 0
		(0..dna1.length-1).each do |i|
			if dna1[i] != dna2[i]
			hamming += 1
			end
		end
		hamming
	end
end
