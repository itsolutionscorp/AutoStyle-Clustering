def combine_anagrams(words)
	anagrams_hash = Hash.new(0)
	words.each { |word|  anagrams_hash[word.downcase.chars.sort.join] = [] }
	words.each { |word|  anagrams_hash[word.downcase.chars.sort.join]  << word }
	anagrams = []
	anagrams_hash.each { | key, word_set |  anagrams << word_set }
	return anagrams
		
end





## input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
##  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
## HINT: you can quickly tell if two words are anagrams by sorting their
##  letters, keeping in mind that upper vs lowercase doesn't matter

my_input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

print combine_anagrams(my_input)