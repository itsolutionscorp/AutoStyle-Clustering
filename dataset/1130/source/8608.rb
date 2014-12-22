def combine_anagrams(words)
	hashMap = Hash.new()
	words.each do |word|
		sortedWord = word.downcase.chars.sort.join
		if(hashMap[sortedWord] == nil)
			hashMap[sortedWord] = Array.new()
		end
		hashMap[sortedWord] = hashMap[sortedWord].push(word)
	end
	words2 = []
	i = -1
	hashMap.each_value do |value|
		words2.insert(i, value)
		i = i + 1
		
	end
	words2
end

puts combine_anagrams(["hello", "olLeh", "bye"])
