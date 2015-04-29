class Hamming 
	def self.compute(strand_1, strand_2)
			short_strand, long_strand = [strand_1,strand_2].sort_by { |strand| strand.length}
			new_long_strand = long_strand[0..(short_strand.length-1)]
			calculate_hamming_distance(short_strand, new_long_strand)
	end

	private
		def self.calculate_hamming_distance(strand_1, strand_2)
			hamming_distance = 0
			strand_1.length.times do |i|
	     	hamming_distance += 1 unless strand_1[i] == strand_2[i]
			end
			hamming_distance
		end
end
