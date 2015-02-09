def combine_anagrams(words)
	results = []
	
	words.each do |word|
		
		added = false
	
		results.each do |result|
		
			if word.downcase.split('').sort == result[0].downcase.split('').sort then
				result << word
				added = true
				break
			end
		end

		if !added then
			results << [word]
		end
	end
	#results.each {|result| puts result.join(',')}
	results
end

# input:
# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter