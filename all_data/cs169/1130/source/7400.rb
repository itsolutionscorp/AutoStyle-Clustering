def combine_anagrams(words)
	anagrams = {};
	words.each do |word|
		index = word.downcase.chars.sort.join
		if !anagrams[index] then anagrams[index] = [] end
		anagrams[index] << word
	end
	return anagrams.values
end