module Hamming
	def self.compute (strand_a, strand_b)
		# We've run out of chars in one or both strings. No more differences.
		if strand_a == '' or strand_b == ''
			return 0

		# First chars not equal, Hamm diff +1, check remainder of strings
		elsif strand_a[0] != strand_b[0]
			return 1 + compute(strand_a[1..-1], strand_b[1..-1])

		# First chars equal, continue checking remainder of strings
		else
			return compute(strand_a[1..-1], strand_b[1..-1])
		end
	end
end
