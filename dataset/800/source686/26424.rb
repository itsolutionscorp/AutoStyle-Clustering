#!/usr/bin/ruby
def combine_anagrams(words)
 anagram_hash = Hash.new()
 words.each { |word|
	base = word.downcase.chars.sort.join
	
	anagram_list = anagram_hash[base]
	if(anagram_list == nil) 
		anagram_hash[base] = word
	else
		anagram_hash[base] << word
	end
	
 }

 anagram_grouping = []
 anagram_hash.each { |key, value|
	anagram_grouping << value
 }
 return anagram_grouping
end

anagram_test = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream']
puts combine_anagrams(anagram_test)