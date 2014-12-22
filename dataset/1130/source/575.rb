def combine_anagrams(words)
	hash = {}
	words.each do |word|
		key = word.downcase.split('').sort.join('')
		hash[key] ||= []
		hash[key] << word
	end
	hash.values
end