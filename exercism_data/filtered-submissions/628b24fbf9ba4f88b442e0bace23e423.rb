def compute(starting_strand, strand_to_be_checked)

		starting = starting_strand.each_char.to_a
		to_be_checked = strand_to_be_checked.each_char.to_a
		hamming_number = 0

		while starting.size > to_be_checked.size
		starting.pop
		end
		while to_be_checked.size > starting.size
		to_be_checked.pop
		end

		for i in 0..starting.size
			if starting[i] != to_be_checked[i]
				hamming_number += 1
			end
		end
		return hamming_number
	end