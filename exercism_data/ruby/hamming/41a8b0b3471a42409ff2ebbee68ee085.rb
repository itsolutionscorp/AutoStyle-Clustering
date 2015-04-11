class Hamming
	def self.compute(strand_a, strand_b)
		strand_a.split('').zip(strand_b.split('')).count { |(a,b)| a && b && a != b }
	end
end
