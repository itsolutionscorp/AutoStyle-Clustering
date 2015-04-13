def compute(first_strand, second_strand)

		if first_strand == second_strand
			0
		else
			difference = 0


			min_length = [first_strand.length, second_strand.length].min

			(0..(min_length-1)).each do | index|
				if first_strand[index] != second_strand[index]
					difference += 1
				end
			end
			difference
		end
	end