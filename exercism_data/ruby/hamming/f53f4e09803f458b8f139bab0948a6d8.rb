class Hamming
	def self.compute(strand1, strand2)
		base_pairs(strand1, strand2).count { |base1, base2| base1 != base2 }
	end

	def self.base_pairs(strand1, strand2)
		strand1.chars.zip(strand2.chars)
	end
end
