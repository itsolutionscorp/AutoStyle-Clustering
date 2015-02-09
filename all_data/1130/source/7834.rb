def combine_anagrams(words)

	hash = Hash.new
	words.each do |word|
		sortedword = word.downcase.scan(/./).sort.join
		if !hash.has_key?(sortedword)
			hash[sortedword] = Array.new
		end
		hash[sortedword] << word
	end
	return hash.values
end
