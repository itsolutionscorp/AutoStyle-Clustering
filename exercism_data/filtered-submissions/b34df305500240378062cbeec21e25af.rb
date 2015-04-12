def compute(first_string, second_string)
		distance = 0
		first_string.split("").each_with_index do |character, index|
			if character != second_string.split("")[index]
				distance = distance+1
			end
		end
		distance
	end