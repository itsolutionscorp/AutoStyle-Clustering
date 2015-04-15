def compute(word1, word2)
		dist = 0
		0.upto( ([word1.size, word2.size].min - 1)) do |i|
			dist += 1 unless word1[i] == word2[i]
		end
		dist
	end