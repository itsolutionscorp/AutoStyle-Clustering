def combine_anagrams(words)
	hash = Hash.new { |hash, key| hash[key] = [] }
	words.each do |word|
		normalized_word = word.downcase.chars.sort.join
		hash[normalized_word] << word
	end
	hash.values
end