def combine_anagrams(words)
	h = Hash.new
	words.each { |word|
		letters = word.downcase.each_char.sort
		if h[letters]
			h[letters].push(word)
		else
			h[letters] = [word]
		end
	}
	return h.values
end
# input: 


words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

combine_anagrams(words)
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
