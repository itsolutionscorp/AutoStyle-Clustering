def combine_anagrams(words)
	anagrams = Hash.new
	words.each do |word|
		k = word.downcase.chars.to_a.sort
		if anagrams[k] == nil
			anagrams[k] = [word]
		else
			anagrams[k] << word
		end
	end
	return anagrams.values
end
