def compute(first, second)

		distance = 0
		return distance if first == second


		first = first.split(//)
		second = second.split(//)


		while !first.empty? && !second.empty?
			distance += 1 if first.shift != second.shift
		end
		distance
	end