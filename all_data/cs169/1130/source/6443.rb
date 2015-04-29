def combine_anagrams(words)
	result = []
	words_copy = words
	while words_copy.length != 0
		group = []
		key = words_copy[0].downcase.chars.sort.join
		words.each do |c|
			if c.downcase.chars.sort.join == key
				group << c
			end
		end
		
		result << group
		words_copy = words_copy - group
	end
	
	return result
end