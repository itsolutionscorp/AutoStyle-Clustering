def combine_anagrams(words)
	h = Hash.new 0
	a = []
	words.each { |word|
		w = word.downcase.chars.sort.join
		if (h.has_key? w)
			a[h[w]] = a[h[w]] + [word]
		else
			h[w] = a.length
			a[h[w]] = [word]
		end
	}
	
	a
end

