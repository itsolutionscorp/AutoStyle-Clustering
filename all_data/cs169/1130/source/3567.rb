
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	patterns = []
	anagrams = []
	words.each do |word|
		pattern = word.downcase.split('').sort
		if patterns.index(pattern)
			idx = patterns.index(pattern)
			anagrams[idx] << word
		else
			patterns << pattern
			anagrams << [word]
		end
	end
	anagrams
end

if __FILE__ == $0
	input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
	puts combine_anagrams(input)
end