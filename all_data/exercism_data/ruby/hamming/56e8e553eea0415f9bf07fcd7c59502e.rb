module Hamming
	extend self

	def compute(strand_1, strand_2)
		shorter_strand, longer_strand = sort_strands(strand_1, strand_2)
		shorter_strand.each_with_index.count { |n, i| longer_strand[i] != n }
	end

	def sort_strands(strand_1, strand_2)
		return [strand_1.chars, strand_2.chars].sort
	end
end
