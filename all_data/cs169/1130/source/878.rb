def anagrams(w1, w2)
	w1.downcase.split('').sort == w2.downcase.split('').sort
end

def combine_anagrams(words)
	res = []
	words.each do |w|
		res += [words.select {|v| anagrams(w, v)}]
	end
	res.uniq
end


# puts combine_anagrams([])

# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])