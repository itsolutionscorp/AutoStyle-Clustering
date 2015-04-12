def compute(word1, word2)
		dist = 0
		0.upto( ([word1.size, word2.size].min - 1)) do |i|
			if word1[i] != word2[i]
				dist = dist + 1 
			end
		end
		dist
	end