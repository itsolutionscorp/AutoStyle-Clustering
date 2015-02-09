def combine_anagrams(words)
	if (words == nil or words.length < 2)
		return words
	end
	results = Hash.new {|h,k| h[k] = Array.new}
	words.each do |word|
		worddown = word.to_s.downcase.chars.sort.join 
		results[worddown].push(word)
		results[worddown]
	end
	return results.values
end

p combine_anagrams(nil)

p combine_anagrams([])

p combine_anagrams('a')

p combine_anagrams(["A","a","this","THIS","b"])

p combine_anagrams(['&'])

p combine_anagrams(["a", 100])

p combine_anagrams(["A", "a", "a", "a","b", "b","c", "D", "d"])

p combine_anagrams(['pots', 'spot', 'stop', 'tops', 'tops','spots', 'stops', 'sausage'])

p combine_anagrams(['Cars'])

puts combine_anagrams(['Cars', 'FOR', 'potatoes', 'Cars','racs', 'four', 'screAm', 'scAr', 'Cars','cReams', 'screAm']).inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

puts combine_anagrams(['C', 'C']).inspect
