def compute(strand_one, strand_two)
		strand_array_one = strand_one.each_char.to_a
		strand_array_two = strand_two.each_char.to_a
		distance = 0

		strand_array_one.zip(strand_array_two.each) do |one, two|
			if one != two
				distance += 1
			end
		end

		return distance
	end