def compute(first_strand, second_strand)
		#if the strands are equal just return zero. this is going to be faster than looping
		if first_strand == second_strand
			0
		else
			difference = 0

			#only want to test up to the smallest length.
			min_length = [first_strand.length, second_strand.length].min

			min_length.times do | index|
				difference += 1 if first_strand[index] != second_strand[index]
			end
			difference
		end
	end