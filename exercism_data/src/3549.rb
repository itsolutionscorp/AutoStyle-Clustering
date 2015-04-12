class Hamming

	# Computes the Hamming distance between two strands
	def compute(strand1, strand2)
		raise ArgumentError unless (strand1.is_a?(String) && strand1.is_a?(String))

		distance = 0
		chars1 = strand1.chars
		chars2 = strand2.chars

		smaller_strand_size = [strand1.size, strand2.size].min
		for i in 0..(smaller_strand_size -1)
			distance += 1 unless chars1[i] == chars2[i]
		end
		
		distance
	end

end
