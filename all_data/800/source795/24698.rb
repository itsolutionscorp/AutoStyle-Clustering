def combine_anagrams(words)
	tempHash = Hash.new	
	arrayOfGroups = []
	arrayOfGroupsHash = Hash.new
	words.each do |word|
	sortedWord = word.chars.sort { |a, b| a.casecmp(b) } .join
	tempHash[word] = sortedWord
	end
	tempHash.each do |key,value|
		tempArray = [];
		#puts 'Finding Anagrams for '+value
		#puts 'length of words'+words.length.to_s
		words.each do |word|
			if word.chars.sort { |a, b| a.casecmp(b) } .join.downcase == value.downcase
			tempArray.push(word)
			#words.delete(word)
			end
		end
		if tempArray.length !=0
			#arrayOfGroups.push(tempArray)
			arrayOfGroupsHash[value.downcase] = tempArray
		end
	end
	arrayOfGroupsHash.each do |key,value|
	#puts 'Pushing values for '+key
		arrayOfGroups.push(value)
	end
	return arrayOfGroups
end 

inputArray = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream']

result = combine_anagrams(inputArray)
result.each do |individualArray|
puts 'Contents in this Array'
individualArray.each do |word|
puts 'Anagrams: '+word
end
end