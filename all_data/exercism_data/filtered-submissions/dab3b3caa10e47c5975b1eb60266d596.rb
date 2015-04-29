def compute(first_strand, second_strand)

		first_strand_arr = first_strand.upcase.chars
		second_strand_arr = second_strand.upcase.chars
		index = 0
		hamming_distance = 0


		number_of_nucleotides = ( first_strand.size > second_strand.size ? second_strand.size : first_strand.size )
		until index >= number_of_nucleotides do
			if first_strand_arr[index] != second_strand_arr[index] then hamming_distance += 1 end
			index += 1
		end

		return hamming_distance

	end