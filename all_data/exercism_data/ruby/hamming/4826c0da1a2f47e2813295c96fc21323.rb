class Hamming
	def self.compute arg1, arg2
		hamming_distance = 0
		arg1.split('').each_with_index do |char, i|
			if char != arg2[i]
				hamming_distance += 1
			end
		end
		hamming_distance
	end
end

# puts Hamming.compute("a", "a")
