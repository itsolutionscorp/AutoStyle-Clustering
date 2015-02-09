def combine_anagrams(words)
	result = [];
	sorted = words.sort
	scrambled = sorted.collect {|x| y = x.downcase.chars.sort.join }
	scrambled.uniq!
	result = scrambled.collect {|x| words.select {|y| y.downcase.chars.sort.join == x} }
end

#print combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] ); 
# output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]