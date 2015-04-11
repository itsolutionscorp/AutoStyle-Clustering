class Hamming
	def self.compute(first_string, second_string)
		distance = 0
		first_string.split("").each_with_index do |char, index|
			if char != second_string.split("")[index]
				distance += 1
			end
		end
		distance
	end
end
