def compute(a, b)
		length = [a, b].min.length
		dist = 0
		length.times do |i|
			dist += 1 if a[i] != b[i]
		end
		dist
	end