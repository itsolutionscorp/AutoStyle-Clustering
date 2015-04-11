class Hamming
	def self.compute(input1, input2)
		raise ArgumentError, "Inputs are not of equal length" unless input1.length == input2.length
		hamming_distance = 0
		for i in 0..input1.length - 1
			if input1[i] != input2[i]
				hamming_distance += 1
			end
		end
		return hamming_distance
	end
end
