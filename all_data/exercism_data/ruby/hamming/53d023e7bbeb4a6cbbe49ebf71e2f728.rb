#Hamming class computes the hamming difference between two DNA base sequences. e.g. "AGT" "ATT" has a hamming distance of one
#If both sequences are not the same length, it ignores teh extra length of the longer when computing HHaming Distance
class Hamming 

	def self.compute(strand1, strand2)
		seq_size = [strand1, strand2].min_by { |x| x.length }.length

		hamming_distance = 0
		seq_size.times do |index|

			if ( strand1[index] != strand2[index] ) then 
				hamming_distance += 1 
			end

		end

		hamming_distance

	end

end
