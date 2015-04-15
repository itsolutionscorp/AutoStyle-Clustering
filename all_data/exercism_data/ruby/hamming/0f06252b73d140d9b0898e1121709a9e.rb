class Hamming

	def self.compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).select { |pair| !(pair[0] == pair[1]) }.count
	end

end
