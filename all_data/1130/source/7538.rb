
 def combine_anagrams(words)
	anagrams=words.group_by { |element| element.downcase.chars.sort }.values
	return anagrams
 end

#arr = ['A', 'a', 'cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#combine_anagrams(arr)
 
