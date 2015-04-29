def combine_anagrams(words)
	loopit = (words == []) ? false : true
	sorted_words = []
	while loopit
		current_loop = []
		current_word_sorted = words.first.downcase.chars.sort.join
		words.each { |word|
			if current_word_sorted == word.downcase.chars.sort.join
				current_loop << word
			end
		}
		sorted_words << current_loop
		words -= current_loop
		if words == []
			loopit = false
		end
	end
	return sorted_words.sort
end
