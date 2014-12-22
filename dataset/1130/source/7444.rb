def combine_anagrams(words)
	list = [] 
	dataDict = {}
	words.each do |word| 
		anagramized = word.downcase.split('').sort.join
		dataDict.include?(anagramized) ? dataDict[anagramized].push(word): dataDict[anagramized] = [word]
	end
	return dataDict.values
end  

