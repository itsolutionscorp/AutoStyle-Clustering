def combine_anagrams(words)
	sorted_words = Hash.new()
	words.each { |word|
		sorted_word = word.downcase.scan(/\w/).sort.join
		if not sorted_words.has_key?(sorted_word)
			sorted_words[sorted_word] = Array.new
		end
		sorted_words[sorted_word] << word
	}
	return sorted_words.values
end

words = ['cars', 'for', 'potatoes', 'RACS', 'four','scar', 'creams', 'scream']

p combine_anagrams(words)