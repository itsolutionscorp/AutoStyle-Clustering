def combine_anagrams(words)
	@words_hash = words.each_with_object(Hash.new []) do |word, hash|
	  hash[word.chars.sort] += [word]
	end
	return @words_hash.values
end