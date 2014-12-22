#require 'pp'

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

    
def combine_anagrams(words)
	return words.group_by { |element| element.downcase.chars.sort }.values
end

#combine_anagrams(input)


