def are_anagrams(a, b)
	a.downcase.split(//).sort() == b.downcase.split(//).sort()
end

def find_anagrams(word, words)
	words.select { |w| are_anagrams(word, w) }
end

def combine_anagrams(words)
	words.map { |w| find_anagrams(w, words) } .uniq
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) 