# hamming -- exercism.io
# author: fractalic

module Hamming
	# Compute the Hamming distance for two DNA strands.
	# The Hamming distance is defined as the number of point mutations.
	# For unequal strands, the Hamming distance is formally undefined.
	def Hamming.compute(strand1, strand2)
		distance = 0

		strand1.split('').zip(strand2.split('')) do |base1, base2|
			distance += 1 if base2 != base1 and base2 != nil
		end
		
		return distance
	end
end
