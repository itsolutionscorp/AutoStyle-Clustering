module Hamming
	def self.compute(strand1, strand2)
    combined_strands = strand1.chars.zip(strand2.chars)
    combined_strands.count { |x| x[0] != x[1] && x[0] != nil && x[1] != nil }
	end
end
