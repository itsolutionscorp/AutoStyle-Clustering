def compute(first, second)
		first_array = first.split(//)
		second_array = second.split(//)

		if first_array.count <= second_array.count
			nucleo_size = first_array.count
		elsif first_array.count > second_array.count
			nucleo_size = second_array.count
		end

		n = 0
		diff_counter = 0

		nucleo_size.times do
			if first_array[n] != second_array[n]
				diff_counter += 1
			end
			n += 1
		end

		return diff_counter

	end