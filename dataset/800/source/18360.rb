def combine_anagrams(words)
	h = {}
	r = []

	words.each{|x| h[x.downcase.chars.sort.join] = []}
	words.each{|x| h[x.downcase.chars.sort.join] << x}
	h.each{|k,v| r<< h[k] }
	return r
end

#print combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
#print combine_anagrams ['Hello','hello']
