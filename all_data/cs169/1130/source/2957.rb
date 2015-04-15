def combine_anagrams(words)
	word_hash = Hash.new { |hash, key| hash[key] = Array.new }
	words.each { |word| word_hash[word.downcase.chars.sort.join] << word }
	puts word_hash.inspect
	return word_hash.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect