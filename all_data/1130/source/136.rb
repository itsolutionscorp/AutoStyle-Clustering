def combine_anagrams(words)
	result = []
	
	words.each do |word|
		newGroup = true
		
		result.each do |group|
			if word.downcase.each_char.sort.to_s == group[0].downcase.each_char.sort.to_s
				group.push(word)
				newGroup = false
				break
			end
		end
		
		if newGroup
			result.push([word])
		end
	end
	
	return result
end