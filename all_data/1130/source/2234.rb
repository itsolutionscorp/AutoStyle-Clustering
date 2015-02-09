def combine_anagrams(words)
	m = words.inject({}) {|m, w| k = w.downcase.chars.sort.join; m[k] = (m[k] || []) << w; m}
	m.values.reduce [], :<<
end

combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
