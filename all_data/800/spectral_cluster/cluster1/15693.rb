def hash_to_array hash
	array = []
	hash.each { |key,value|
		array.push value
	}
	return array
end

def combine_anagrams words
	if words == []
		words
	end
	anagram_groups = {}

	words.each { |word|
		sortedWord = word.downcase.chars.sort.join
		if anagram_groups[sortedWord]
			anagram_groups[sortedWord].push word
		else
			words = []
			words.push word
			anagram_groups[sortedWord] = words
		end
	}
	return hash_to_array anagram_groups
end