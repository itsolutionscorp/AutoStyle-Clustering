# input:
# words=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	hash = {}
	#words.each{ |w| hash[w.downcase.chars.sort.join] ||= [] << w }
	words.each do |w| 
		k = w.downcase.chars.sort.join
		if hash[k]
			hash[k] << w
		else
			hash[k] = [w]
		end
	end
	hash.values
end

