def combine_anagrams(words)
	result = []
	processed = []
	words.each do |word1|
		if processed.include? word1
		else
			innerRes = [word1]
			words.each do |word2|
				if word1 == word2
				else
					if word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
						innerRes = innerRes + [ word2 ]
						processed = processed + [ word2 ]
					end
				end
			end
			result = result + [ innerRes ]
		end
	end
	return result
end