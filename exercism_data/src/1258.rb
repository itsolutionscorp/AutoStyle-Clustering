def compute(first, second)
		min_string_length = [first.length, second.length].min
		distance = 0

		0.upto(min_string_length - 1) do |index|
			if first[index] != second[index]
				distance += 1
			end
		end
		return distance
	end