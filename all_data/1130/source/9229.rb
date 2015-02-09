def combine_anagrams(words)
 	hash =  Hash.new{|h,k| h[k] = [] }
 	words.each do |word|
		sorted_word = word.downcase.chars.sort{ |a, b| a.casecmp(b) }.join
		hash[sorted_word] << word
 	end
 	return hash.values
end

#p combine_anagrams(['cars', 'A', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])