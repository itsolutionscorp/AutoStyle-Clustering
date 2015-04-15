def compute(first, second)

		first = first.chars.to_a
		second = second.chars.to_a

		difference = 0
		increment = 0
		first.each do |f|
			s = second[increment]
			increment += 1
			if f != s
				difference += 1
			else
			end
		end
		return difference
	end