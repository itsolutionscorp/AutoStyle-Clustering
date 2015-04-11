module Hamming

	def self.compute(strand1, strand2)
		count = 0;
		for i in 0...short_strand_size(strand1, strand2)
			count += 1 unless strand1[i] == strand2[i]
		end
		count
	end

	private
	# Given 2 strands, it returns the length of the shortest
	def self.short_strand_size(strand1, strand2)
		(strand1.size < strand2.size) ? (strand1.size) : (strand2.size)
	end

end
