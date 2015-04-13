def compute(a,b)
		difference = 0

		min_length = [a.length, b.length].min

		min_length.times do |n|
			difference += 1 if a[n] != b[n]
		end

		return difference
	end