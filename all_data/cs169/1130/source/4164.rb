def combine_anagrams(words)
	anagrams = {}
	words.each do |word|
		sorted = word.downcase.chars.sort.join
		if (anagrams[sorted] == nil)
			anagrams[sorted] = []
		end
		anagrams[sorted] << word
	end
	result = []
	anagrams.each_value do |value|
		result << value
	end
	result
end

