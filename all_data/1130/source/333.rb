# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
    anagrams = Array.new
	anagram_hash = Hash.new
	words.each { |word|
		word_sorted = word.downcase.chars.sort.join
		if anagram_hash.has_key?(word_sorted) 
			anagrams[anagram_hash[word_sorted]].push(word)
		else
			anagram_hash[word_sorted] = anagrams.length;
		    anagrams.push(Array[word])
		end
	}
	
	return anagrams
end


combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

