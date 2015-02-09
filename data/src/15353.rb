

def combine_anagrams(words)
	
	
	
	return words.group_by { |word| word.downcase.chars.sort }.values
	

end

words = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts combine_anagrams(words)