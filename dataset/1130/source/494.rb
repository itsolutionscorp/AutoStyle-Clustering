def combine_anagrams(words)
	anagrams = Hash.new()
	combined = Array.new()
	words.each do |word|
		sorted = word.downcase.split('').sort.join
		if (anagrams[sorted] == nil)
			anagrams[sorted] = Array.[](word)
		else
			anagrams[sorted].insert(-1, word)
		end
	end
	anagrams.each do |sorted, words|
		combined.insert(-1, words)
	end
	return combined
end

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(input)