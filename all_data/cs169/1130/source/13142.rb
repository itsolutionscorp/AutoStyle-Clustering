def combine_anagrams(words)
	a = []
	@words_hash = words.each_with_object(Hash.new []) do |word, hash|
		hash[word.downcase.chars.sort] += [word]
	end
	@words_hash.each { |key,value| a<<value}
	return a
end
