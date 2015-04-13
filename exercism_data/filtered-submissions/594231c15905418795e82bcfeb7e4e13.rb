def compute(a, b)
		count = 0
		a.each_char.with_index {|x, i| count += 1 if x != b[i]}
		count
	end