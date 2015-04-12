def compute(stringA, stringB)
		hamming = 0
		counter = 0
		stringA.split("").each do |i|
			if (stringB[counter].nil?)
				break
			end
			if (i != stringB[counter])
				hamming += 1
			end
			counter += 1
		end
		return hamming
	end