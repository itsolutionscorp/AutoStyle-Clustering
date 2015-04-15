
def combine_anagrams(words)
	h = Hash.new
	words.each do |word|
		w_sorted = word.downcase.chars.sort.join
		if(nil == h[w_sorted])
			h[w_sorted] = Array.new
		end
		h[w_sorted][h[w_sorted].length] = word
	end
	return h.values
end
