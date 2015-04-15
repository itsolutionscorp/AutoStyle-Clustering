


def combine_anagrams(words)
	words_h = Hash.new{|hash, key| hash[key] = Array.new}

	words.each do |x|
		words_h[x.downcase.chars.sort.join] << x
	end
	return words_h.values
end
print combine_anagrams(['Cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'])
