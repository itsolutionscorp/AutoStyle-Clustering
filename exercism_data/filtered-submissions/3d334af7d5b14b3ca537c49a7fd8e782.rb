def compute(strand_1, strand_2)
		a, b = strand_1.split(//), strand_2.split(//)
		a.each_with_index.map { |e, i| e == b[i]}.select{ |e| e == false}.count
	end