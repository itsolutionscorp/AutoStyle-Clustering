def combine_anagrams(words)
	anagram_groups = Hash.new

	words.each do |word|
		base_word = word.downcase.split("").sort.join
		anagram_groups[base_word] = Array.new if (!anagram_groups[base_word])
		
		anagram_groups[base_word] << word
	end
	
	return anagram_groups.values
end
