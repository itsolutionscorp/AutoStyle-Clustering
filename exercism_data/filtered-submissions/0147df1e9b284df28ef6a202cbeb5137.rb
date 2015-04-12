class Hamming

	def compute(stringA, stringB)
		hamming = 0
		
		stringA.split("").each_with_index do |char, index|
			if (stringB[index].nil?)
				return hamming
			end
			if (char != stringB[index])
				hamming += 1
			end			
		end
		return hamming
	end


end
