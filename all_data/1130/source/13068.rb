def combine_anagrams(words)
	groups = Hash.new
	words.each do |word|
		sorted_word = word.downcase.chars.sort.join
		if groups.has_key?(sorted_word)
			groups[sorted_word] << word
		else
			groups[sorted_word] = [word]
		end	
	end
	groups.values
end