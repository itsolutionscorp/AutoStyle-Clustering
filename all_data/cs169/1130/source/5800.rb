def combine_anagrams(words)
	groups = Hash.new()
	words.each do |word| 
		tmp = word.downcase.chars.sort.join
		if groups.has_key?(tmp)
			groups[tmp] = groups[tmp].push(word)
		else
			groups[tmp] = Array.[](word)
		end
	end
	return groups.values
end


# input:
#words = ['Cars', 'for', 'poTatoes', 'racs', 'four','scar', 'creams', 'scream']
# => [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
#puts combine_anagrams(words)