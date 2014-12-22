def combine_anagrams(words)
	a_hash = Hash.new
	words.each do |wrd|
		w_key = wrd.downcase.chars.sort.join
		a_hash[w_key] = [] unless a_hash.has_key?(w_key)
		a_hash[w_key].push(wrd)
	end
	return a_hash.values
end
