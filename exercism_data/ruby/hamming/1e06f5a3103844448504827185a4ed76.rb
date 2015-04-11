class Hamming
	def self.compute(base_str, compare_str)
		hamming_counter = 0
		base_str_array = base_str.split(//)
		compare_str_array = compare_str.split(//)

		(0..base_str_array.length).each do |i|
			if (base_str_array[i] != nil) and (compare_str_array[i] != nil)
				if base_str_array[i] != compare_str_array[i]
					hamming_counter += 1
				end
			end
		end

		return hamming_counter
	end
end
