def combine_anagrams(words)
	output_array = []
	words.each do |word1|
		tmp = []
		words.each do |word2|
			if (word2.downcase.split(//).sort == word1.downcase.split(//).sort)
				tmp.push(word2)
			end
		end
		output_array.push(tmp)
	end
	return output_array.uniq
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
