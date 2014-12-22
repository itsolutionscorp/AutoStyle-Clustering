def combine_anagrams(words)
	anagrams = Hash.new
	words.each do |word|
		wh = word.downcase.split(//).sort.join
		if(anagrams.has_key?(wh)) then
			anagrams[wh] << word
		else
			anagrams[wh] = Array.new
			anagrams[wh] << word
		end
	end
	anagrams.values
end
 
#puts combine_anagrams (['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])