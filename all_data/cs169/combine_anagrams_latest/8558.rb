def combine_anagrams(words)
	groups = Hash.new([])
	words.each {|word| groups[word.chars.sort(&:casecmp).join] += [word]}
	return groups.values
end

combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream']) 
