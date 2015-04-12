class Hamming

	def Hamming.compute (sequence_one, sequence_two) 
		index = 0
		mutations = 0
		while index < [sequence_one.length, sequence_two.length].min
			if sequence_one[index] != sequence_two[index]
				mutations += 1
			end
			index += 1
		end
		return mutations
	end
end
