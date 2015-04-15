def combine_anagrams(words)
	wordHash=Hash.new
	words.each{|word|
		sortedword=word.downcase.chars.sort.join
		if wordHash[sortedword].nil?
			newWord = [word]
			wordHash[sortedword] = newWord
		else
			wordAlreadyPresent = wordHash[sortedword]
			wordAlreadyPresent.push(word)
			wordHash[sortedword]=wordAlreadyPresent
		end
	}
	listToReturn = Array.new
	wordHash.each{|key,value| 
		listToReturn.push(value)
	}
	return listToReturn
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
