def compute(first_strand, second_strand)

		sequence_one = first_strand.chars
		sequence_two = second_strand.chars


		h_dist = 0
		for i in 0..sequence_one.length
			puts "i:
			if sequence_one[i] != sequence_two[i]
				then h_dist += 1
			end
		end


		return h_dist
	end