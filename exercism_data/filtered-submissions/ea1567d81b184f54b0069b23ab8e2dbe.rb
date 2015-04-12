def compute(first, second)
		first = first.chars
		second = second.chars

		distance = 0
		while !first.empty? && !second.empty?
			distance += 1 if first.shift != second.shift
		end
		distance
	end
end  # end Module Hamming