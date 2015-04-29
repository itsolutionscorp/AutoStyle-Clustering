def combine_anagrams(words)

	anagrams = words.group_by { |word| word.downcase.chars.sort }.values
	
	return anagrams
				
end
#words = ['ScAR','Scar', 'scar', 'four', 'creams', 'scream', 'racs']
#combine_anagrams(words)