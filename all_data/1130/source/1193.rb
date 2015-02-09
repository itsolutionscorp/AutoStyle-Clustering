def combine_anagrams(words)

	word_hash = Hash.new
	
	words.each do |word|
		word_key = word.downcase.split('').sort
		
		if word_hash.key?(word_key)
			word_hash[word_key] += [word]
		else
			word_hash[word_key] = [word]
		end
	end 
	
	word_hash.values
end
