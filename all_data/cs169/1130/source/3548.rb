def combine_anagrams(words)
	anagram_hash = Hash.new
	words.each do |w|
		letters = w.upcase.chars.sort.join
		if anagram_hash.has_key?(letters)
			anagram_hash[letters].push(w)
		else
			anagram_hash[letters] = [w]
		end
	end
	
	result = Array.new
	anagram_hash.each_value do |ana_group|
		result.push(ana_group)
	end
	return result
end

test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(test)