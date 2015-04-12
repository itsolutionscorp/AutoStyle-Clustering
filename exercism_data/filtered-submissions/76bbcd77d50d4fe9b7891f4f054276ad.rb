def compute (string_a, string_b)
		distance = 0
		min_length = [string_a.length, string_b.length].min
		min_length.times do |i|
			distance += 1 if string_a[i] != string_b[i]
		end
		distance
	end