def combine_anagrams(words)
	anagrams = Array.new
	words.each do |word|
		found = false
		anagrams.each do |anagram|
			if anagram[0].downcase.chars.to_a.sort == word.downcase.chars.to_a.sort
				anagram << word
				found = true
				break
			end
		end
		unless found
			anagrams << Array[word]
		end
	end
	return anagrams
end
