def combine_anagrams(words)
	sorted_words = {}
	words.each do |word|
		letters = []
		word.downcase.each_char { |char| letters << char }
		
		sorted_word = ''
		letters.sort.each { |char| sorted_word << char }
		
		sorted_words[sorted_word] = [] if sorted_words[sorted_word].nil?
		sorted_words[sorted_word] << word
	end
	
	output = []
	sorted_words.each_value { |word| output << word }
	output
end
