def compute(first_strand, second_strand)
		distance = 0;
		first_strand, second_strand = [first_strand.chars, second_strand.chars].sort { |x, y| x.length <=> y.length }
		first_strand.each_with_index do |ch, i|
			distance += 1 if second_strand[i] != ch
		end
		distance
	end