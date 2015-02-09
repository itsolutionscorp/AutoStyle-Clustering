def combine_anagrams(words)
	h = Hash.new
	words.each{ |word| (h[word.upcase.chars.sort.join] ||= []) << word }
	h.values
end
