def combine_anagrams(words)
	anagram = Hash.new {|hash, key| hash[key] = []}
	for word in words
		anagram[word.upcase.split('').sort] << word
	end
	newAnagr = []
	anagram.each_value do |ana|
		newAnagr.push(ana)
	end
	return newAnagr
end
p combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

