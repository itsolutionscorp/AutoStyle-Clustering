def combine_anagrams(words)
	h = Hash[]
	words.each {|w| h[w.downcase.chars.sort.join] = h[w.downcase.chars.sort.join].to_a << w}
	return h.to_a.collect {|kv| kv[1]}
end

#puts(combine_anagrams(['Cars', 'fOr', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']))