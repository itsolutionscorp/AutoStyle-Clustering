# given an array of words,
# group the words into anagram groups
def combine_anagrams(words)
	groups_array = []
	anagram_array = []
	words.each do |word|
		# generate signature
		signature = word.downcase.each_char.sort.join
		# see if signature is in list
		if groups_array.include?(signature) then
			x = groups_array.index(signature)
			anagram_array[x] << word
		else
			# add new signature
			groups_array << signature
			anagram_array << [word]
		end
	end
	return anagram_array
end

#test it
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

