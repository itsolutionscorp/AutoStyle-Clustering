# input:
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	result = Hash.new
	words.each do |word|
		sorted = word.downcase.chars.sort.join
		if result.has_key?(sorted)
			result[sorted] = result[sorted] << word
		else 
			result[sorted] = [word]
		end
	end
	result.values
end

#test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#print combine_anagrams(test)
