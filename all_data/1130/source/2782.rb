def combine_anagrams(words)

	anagrams = {}
	words.each do |word|
		key = word.downcase.chars.sort.join
		anagrams[key] = [] unless anagrams[key]
		anagrams[key].push word
	end
	
	solution = []
	anagrams.keys.each do |key|
		solution.push anagrams[key]
	end 
	
	return solution
end

# input:
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
