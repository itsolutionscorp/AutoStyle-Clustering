def compute arg1, arg2

		arg1_array = arg1.split('')
		arg2_array = arg2.split('')
		hamming_distance = 0
		arg1_array.each_with_index do |char, i|
			if char != arg2_array[i]



				hamming_distance += 1

			end
		end
		return hamming_distance
	end