module Hamming
	def self.compute (strand_a, strand_b)
		# We've run out of chars in one or both strings. No more differences.
		if (strand_a.empty? || strand_b.empty?)
			return 0		

		else 
			return (strand_a[0] != strand_b[0] ? 1 : 0) + compute(strand_a[1..-1], strand_b[1..-1])
		end
	end
end
