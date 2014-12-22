

def combine_anagrams(words)

	anagrams = {}

	words.each do |word| 
		anagram_key = word.downcase.split('').sort.join
		anagrams[anagram_key] ||= []
		anagrams[anagram_key] << word
	end

	anagrams.values
end

#combine_anagrams(['cARs', 'for', 'poTatoes', 'racs', 'four','scar', 'creams', 'scream'])
