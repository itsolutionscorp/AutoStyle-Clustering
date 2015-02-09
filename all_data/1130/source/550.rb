#!/usr/bin/ruby

def combine_anagrams(words)
	
	words.group_by{|word| 
		s = word.downcase.split(//)
		s = s.sort
		s = s.join
	}.values

end

arr =  combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#print combine_anagrams(['c', 'f', 'p', 'r', 'o','s', 'e', 'a'])
