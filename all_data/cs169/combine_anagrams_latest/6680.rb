def combine_anagrams(words)
	 retHash = {}

	 words.each { |word|
			key = word.downcase.chars.sort.join
			if !retHash[key]
					 retHash[key] = []
			end
			retHash[key] << word
	 }

	 retHash.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect