module Hamming
	def self.compute(strand1, strand2)
    strand1 = strand1.split('')
    strand2 = strand2.split('')
    combined_strands = strand1.zip(strand2)
    combined_strands.count { |x| x[0] != x[1] && x[0] != nil && x[1] != nil }
	end
end
