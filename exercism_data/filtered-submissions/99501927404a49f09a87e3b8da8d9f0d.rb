def compute(strand_1, strand_2)
		shorter_strand, longer_strand = [strand_1.chars, strand_2.chars].sort

		distance = 0

		shorter_strand.each_with_index do |n, i|
			distance += 1 if longer_strand[i] != n
		end

		return distance
	end