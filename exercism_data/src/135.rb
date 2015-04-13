def compute(strand1, strand2)

		strand1_array = strand1.chars.to_a
		strand2_array = strand2.chars.to_a


		distance = 0


		strand1_array.length <= strand2_array.length ? length = strand1_array.length : length = strand2_array.length


		for i in 0..length-1

			if strand1_array[i] != strand2_array[i]
				distance += 1
			end
		end

		return distance
	end