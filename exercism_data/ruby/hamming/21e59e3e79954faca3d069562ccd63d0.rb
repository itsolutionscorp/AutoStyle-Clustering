class Hamming
	def self.compute(strand1, strand2)
		min_length = [strand1.size, strand2.size].min
		chars_from_both_strings = (strand1[0...min_length].chars).zip(strand2[0...min_length].chars)
		chars_from_both_strings.count { |pair| pair[0] != pair[1] }
	end
end
