def combine_anagrams(words)
	h=Hash.new{|h,k| h[k]=[]}
	words.each do |w|
		h[w.downcase.chars.sort.join] << w
	end
	return h.values
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
