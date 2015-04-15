def combine_anagrams(words)
	hash = Hash.new()
	words.each{|word|
		key = word[0].split('').sort.join
		if hash.has_key? key
			hash[key]+=[word]
		else
			hash[key]= [key]
		end
	}
	return hash.values
end
#inputArray = [['cars', 'racs', 'scar'].sort, ['four'], ['for'], ['potatoes'], ['creams', 'scream'].sort]
#puts combine_anagrams(inputArray).to_s
