def combine_anagrams(words)
	anagrams = {}
	words.each do |word|
		sorted_word = word.downcase.chars.sort.join
		if anagrams.key? sorted_word
			anagrams[sorted_word] += [word]
		else
			anagrams[sorted_word] = [word]
		end
	end
	anagrams.values
end

