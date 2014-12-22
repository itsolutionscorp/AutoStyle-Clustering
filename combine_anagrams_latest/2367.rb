def combine_anagrams(words)
	h = Hash.new()
	words.each { |word|
		key = word.downcase.chars.sort.join
		if h.has_key?(key)
			h[key] = h[key] << word
		else
			h[key] = [word]
		end
	}
	h.values
end

