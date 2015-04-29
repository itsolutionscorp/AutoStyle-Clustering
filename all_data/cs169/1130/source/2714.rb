### Part III - anagrams

def combine_anagrams(words)
	storage = Hash.new([])
	for word in words
		key = word.downcase.split("").sort
		storage[key] = []
	end
	
	for word in words
		key = word.downcase.split("").sort
		storage[key].push(word)	
	end
	storage.values
end


if __FILE__ == $0
	anagrams = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
	combine_anagrams(anagrams)
end