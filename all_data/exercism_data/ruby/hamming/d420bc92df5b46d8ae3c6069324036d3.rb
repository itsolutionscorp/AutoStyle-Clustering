# hamming -- exercism.io
# author: fractalic

module Hamming
	# Compute the Hamming distance for two DNA strands.
	# The Hamming distance is defined as the number of point mutations.
	# For unequal strands, the Hamming distance is formally undefined (0 returned).
	def Hamming.compute(strand1, strand2)
		distance = 0

		minlength = 0
		if strand1.length() < strand2.length()
			minlength = strand1.length()
		else
			minlength = strand2.length()
		end

		for i in 0..minlength-1 do
			if (strand1[i] != strand2[i])
				distance += 1
			end
		end

		return distance
	end
end
