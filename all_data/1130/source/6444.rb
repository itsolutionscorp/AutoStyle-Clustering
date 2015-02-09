def combine_anagrams(words)
	ana_hash = Hash.new
	words.each do |word|
		word_sorted = word.downcase.chars.sort.join
		if ana_hash.has_key?(word_sorted)
			ana_hash[word_sorted].push(word)
		else
			ana_hash[word_sorted] = [word]
		end
	end
	return ana_hash.values()
end