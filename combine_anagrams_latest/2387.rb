def combine_anagrams(words)
	map = Hash.new
	
	words.each do |word|
		wordHash = word.downcase.chars.sort.join
		if map.has_key? wordHash
			map[wordHash] << word
		else
			map.store wordHash, [word]
		end
	end
	
	return map.values
end