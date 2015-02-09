# aung maw
# 11 june 2012
# hw #1 part III

def combine_anagrams(words)
	anagrams = words.group_by { |word| word.chars.sort }.values	
end


#array = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#puts combine_anagrams(array).inspect