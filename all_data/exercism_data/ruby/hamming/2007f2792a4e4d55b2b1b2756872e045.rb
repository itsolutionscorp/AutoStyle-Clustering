class Hamming
	def self.compute(strand_a, strand_b)
		pairs = strand_a.chars.zip(strand_b.chars)
		pairs.count do |a, b|
			a != b
		end
	end
end
