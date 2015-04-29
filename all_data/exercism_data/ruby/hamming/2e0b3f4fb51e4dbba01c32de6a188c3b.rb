class Hamming
	def self.compute arg1, arg2
		# for every letter thats different add one to the value.
		arg1_array = arg1.split('')
		arg2_array = arg2.split('')
		hamming_distance = 0
		arg1_array.each_with_index do |char, i|
			if char != arg2_array[i]
				# puts "test cases"
				# puts char
				# puts arg2_array[i]
				hamming_distance += 1
				# puts hamming_distance
			end
		end
		return hamming_distance
	end
end

# puts Hamming.compute("a", "a")
