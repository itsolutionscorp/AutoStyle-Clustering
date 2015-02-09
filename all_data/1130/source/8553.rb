def combine_anagrams(words)
	result = {}
	words.each do |word|
		key = word.downcase.chars.sort.join
		if result.has_key? key
			result[key].push word
		else
			result[key] = [word]
		end
	end
	return result.values
end
