# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	anagrams = {}
	words.each do |word|
	  sword = word.upcase.chars.sort.join
	  anagrams[sword] ||= []
	  anagrams[sword] << word
	end
	return anagrams.values
end

# test cases
#p combine_anagrams(['CARS', 'for', 'potatoes', 'racs', 'four','scar', 'Creams', 'scream'])
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
