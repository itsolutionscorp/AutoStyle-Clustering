def is_an_anagram?(first_word, second_word)
	first_word.chars.sort.join == second_word.chars.sort.join
end

def combine_anagrams(words)
  	#   <YOUR CODE HERE>

	final_anagram_array = []

	while words.length > 0
		final_anagram_array << words.select { |x| is_an_anagram?(words.first.downcase, x.downcase) }
		words = words.reject { |x| is_an_anagram?(words.first.downcase, x.downcase) }
	end

	return final_anagram_array
	
end