def compute(a, b)
		count = 0
		[a.length, b.length].min.times { |i| count += 1 if a[i] != b[i] }

		count
	end