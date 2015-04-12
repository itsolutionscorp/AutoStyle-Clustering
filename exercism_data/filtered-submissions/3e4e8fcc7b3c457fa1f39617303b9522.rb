def compute(strand1, strand2)
		seq_size = [strand1, strand2].min_by { |x| x.length }.length

		hamming_distance = 0
		seq_size.times do |index|

			if ( strand1[index] != strand2[index] ) then 
				hamming_distance += 1 
			end

		end

		return hamming_distance