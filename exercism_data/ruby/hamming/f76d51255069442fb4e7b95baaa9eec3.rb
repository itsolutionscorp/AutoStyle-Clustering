class Hamming
	def self.compute(dna1, dna2)
		dna1.split("").each_with_index.select {|d, index| 
			d != dna2[index]
		}.size
	end
end