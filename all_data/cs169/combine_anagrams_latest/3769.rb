def combine_anagrams(words)
	result = Hash.new 
	words.each do |word|
		key = word.downcase.split(//).sort!
		if result.has_key? key
			result[key].push(word)
		else
			result[key] = [word]
		end
	end

	return result.values
end

#p combine_anagrams  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 