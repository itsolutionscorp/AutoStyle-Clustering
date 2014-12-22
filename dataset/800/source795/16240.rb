
def combine_anagrams(words)
	anagrams = Hash.new { |hash,key| hash[key]=[] }
	words.each { |word| anagrams[word.downcase.chars.sort.join] << word }
	return anagrams.values
end	

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])	
