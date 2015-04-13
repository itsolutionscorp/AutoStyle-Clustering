def compute(first_strand,second_strand)


		diff_counter = 0
		counter = 0
		while counter < first_strand.length && counter < second_strand.length
			if first_strand[counter] != second_strand[counter]
					diff_counter += 1
			end
			counter +=1
		end
		diff_counter
	end