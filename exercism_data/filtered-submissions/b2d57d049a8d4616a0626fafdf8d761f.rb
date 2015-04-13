def compute(first_strand, second_strand)

		if first_strand == second_strand
			0
		else
			difference = 0


			min_length = [first_strand.length, second_strand.length].min

			min_length.times do | index|
				difference += 1 if first_strand[index] != second_strand[index]
			end
			difference
		end
	end