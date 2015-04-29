class Hamming
	def self.compute(strand1, strand2)
		hamming_distance = 0
		strand_length(strand1, strand2).times do |i|
			hamming_distance += 1 unless strands_equal(strand1, strand2, i)
		end
		hamming_distance
	end
	
	private
	def self.strand_length(strand1, strand2)
		[strand1.length, strand2.length].min
	end
	
	def self.strands_equal(strand1, strand2, index)
		strand1[index] == strand2[index]
	end
end
