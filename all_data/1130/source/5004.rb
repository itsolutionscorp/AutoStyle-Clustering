def combine_anagrams(words)
	anagrams_dict = Hash.new{|h, k| h[k] = []}
	words.each {|w| anagrams_dict[w.downcase.chars.sort.join].push(w) }
	return anagrams_dict.values
end



