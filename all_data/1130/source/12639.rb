def combine_anagrams(words)
	grouped = Hash.new([])
	words.each do |word|
		grouped[word.downcase.chars.sort.join] += [word]
	end
	return grouped.values.to_a
end

# pp combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# pp combine_anagrams ['a', 'a', 'A']