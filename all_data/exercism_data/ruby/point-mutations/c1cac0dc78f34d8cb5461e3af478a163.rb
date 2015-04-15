class DNA
	def initialize( original_strand )
		@original_strand = original_strand
	end

	def hamming_distance( compared_strand )
		hamming_distance = 0
		index = 0
		@original_strand.each_char do |molecule|
			break unless compared_strand[index]
			hamming_distance += 1 if molecule != compared_strand[index]
			index += 1
		end
		hamming_distance
	end
end
