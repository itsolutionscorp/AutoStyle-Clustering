# hamming -- exercism.io
# author: fractalic

module Hamming
	# Compute the Hamming distance for two DNA strands.
	# The Hamming distance is defined as the number of point mutations.
	# For unequal strands, the Hamming distance is formally undefined.
	def Hamming.compute(strand1, strand2)
		distance = 0

		minlength = [strand1.length,strand2.length].min

		strands = strand1.split('').zip(strand2.split(''))
		for strand_pair in strands do
			if (strand_pair[0] != strand_pair[1] and
				(strand_pair[0] and strand_pair[1]) != nil)
				distance += 1
			end
		end
		
		return distance
	end
end
