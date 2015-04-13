def compute(first_string, second_string)
		letter_array_one = first_string.split("")
		letter_array_two = second_string.split("")
		final_array      = letter_array_one.zip(letter_array_two)

		results = final_array.select do |first_num, second_num|
			if first_num && second_num
				first_num != second_num
			end
		end

		results.length
	end