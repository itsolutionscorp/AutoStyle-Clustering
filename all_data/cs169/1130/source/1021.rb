# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	result = []
	words.each do |word|
		search_pattern = word.downcase.split(//).sort
		added = false
		result.each do |item|
			if item[0].downcase.split(//).sort == search_pattern then
				item.push(word)
				added = true
			end
		end
		if added == false then result.push([word]) end
		print search_pattern
		print "\n----------------\n"	
	end
	return result
end
