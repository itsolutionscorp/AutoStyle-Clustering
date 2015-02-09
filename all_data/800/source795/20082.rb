def combine_anagrams(words)
	anagram = {}
	words.each do |word|
		gram = word.chars.sort_by(&:downcase).join
		if (anagram[gram])
			anagram[gram].push(word)
		else
			anagram[gram] = [word]
		end
	end
	anagram.values
end