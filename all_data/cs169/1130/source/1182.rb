def combine_anagrams(words)
	h = Hash.new {|hash, key| hash[key] = []}
	words.each {|word| h[word.downcase.chars.to_a.sort.hash] += [word]}
	h.values
end
