# convienence function
def anagram?(word1, word2)
	word1.downcase.split('').sort == word2.downcase.split('').sort
end

def combine_anagrams(words)
	# the groups of anagrams
	result = []

	words.each do |word|

		# the first word must form group of his own to get started
		if result.empty? then
			result.push([word]) 
			next
		end

		# helps to decide if to create new group
		anagram = false
		result.each do |group|
			if anagram?(group[0], word) then
				anagram = true
				group.push(word)
				break
			end
		end
		# the word is not anagram to the groups gathered so far
		# so puts in its own group
		result.push([word]) if not anagram
	end
	return result
end
