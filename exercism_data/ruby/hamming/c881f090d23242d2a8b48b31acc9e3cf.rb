class Hamming
	def self.compute(strand1, strand2)
		
		paired_strands(strand1, strand2).count {|n1, n2| n1 != n2}
	end

	def self.paired_strands(strand1, strand2)
		strand1.chars.zip(strand2.chars)
	end
end
