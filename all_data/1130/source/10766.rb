def combine_anagrams(words)
	result = {}
	words.each do |word|
		key = word.downcase.chars.sort
		if result[key] == nil
			result[key] = [word]
		else
			result[key] = result[key] << word
		end
	end
	return result.values
end

