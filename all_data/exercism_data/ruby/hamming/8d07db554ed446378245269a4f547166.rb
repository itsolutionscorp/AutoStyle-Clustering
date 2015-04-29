class Hamming
	def self.compute (dna1, dna2)
		result = 0
		(0..dna1.length-1).each do |i|
			if dna1[i] != dna2[i]
			result += 1
			end
		end
		result
	end
end
