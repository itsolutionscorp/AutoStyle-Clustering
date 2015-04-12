def compute(first_sequence, second_sequence)
		distance = 0
		first_sequence.each_char.with_index do |char, index|
			distance += 1 if second_sequence[index] && second_sequence[index] != char
		end
		distance
	end