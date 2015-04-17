def compute(first, second)
		# Simply return 0 if there are no differences in the strands
		distance = 0
		return distance if first == second
		
		# Split the strings into arrays of chars
		first = first.split(//)
		second = second.split(//)

		# Loop through both arrays, 
		while !first.empty? && !second.empty?
			distance += 1 if first.shift != second.shift
		end
		distance
	end
end  # end Module Hamming