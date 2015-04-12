def compute(a,b)
		test_length = [a.length, b.length].min
		distance = 0
		test_length.times do |i|
			distance += 1 if a[i] != b[i]
		end
		distance
	end