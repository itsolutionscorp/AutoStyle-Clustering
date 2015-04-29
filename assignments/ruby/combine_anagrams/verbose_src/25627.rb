def combine_anagrams(words)

	res = Hash.new([])
	
	words.each do |word|
		w = word.downcase.chars.sort.join
		res[w] = res[w] + [word]
	end
	
	res.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]