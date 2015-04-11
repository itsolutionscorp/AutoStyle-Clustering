class Hamming

	# Computes the Hamming distance between two strands
	def self.compute(strand1, strand2)
		raise ArgumentError unless (strand1.is_a?(String) && strand1.is_a?(String))

		distance = 0
		chars1 = strand1.chars
		chars2 = strand2.chars

		smaller_strand_size = [strand1.size, strand2.size].min
		smaller_strand_size.times do |i|
			distance += 1 unless chars1[i] == chars2[i]
		end

		distance
	end

end
