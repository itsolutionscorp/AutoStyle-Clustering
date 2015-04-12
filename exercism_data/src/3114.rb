def compute(a, b)
		a, b = b, a if a.size > b.size
		distance = 0
		a.chars.each_with_index do |e, i|
			distance += 1 if e != b.chars.to_a[i]
		end
		distance
	end