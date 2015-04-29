def combine_anagrams(words)

	return words unless words.length > 1
	
	result = [[words[0]]]

	for i in 1..words.length - 1
		inserted = false		
		for j in 0..result.length - 1
			if words[i].downcase.chars.sort.join == result[j][0].downcase.chars.sort.join
				result[j].push (words[i])
				inserted = true
				break
			end
		end
		if !inserted
			result.push [words[i]]
		end			
	end
	
	return result
	
end