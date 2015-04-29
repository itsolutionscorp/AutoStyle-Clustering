# input: 
example = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	anagrams = {}
	anagrams.default = []
	words.each do |word|
		key = word.downcase.chars.sort.join
		anagrams[key] = anagrams[key] + [word]
	end
	anagrams.values
end

p combine_anagrams(example)