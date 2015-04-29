def char_array(string)
	char_array = []
	string.downcase.each_char {|c| char_array << c}
	return char_array.sort
end

def combine_anagrams(words)
	anagram_array=[]
	words.each do 
		|w| 
		matched = false
		anagram_array.each do
			|a|
			if char_array(w)==char_array(a.first)
				matched=true
				a << w
			end
		end
		anagram_array<<[w] unless matched
		end
	return anagram_array
end

print combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream', 'FOR'])

  
