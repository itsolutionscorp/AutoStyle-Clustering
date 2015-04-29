#!/usr/bin/env ruby -wKU

# hw1_3
# Alex Perucchini

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their # letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words) 
	anagrams = Hash.new(0)
	anagrams = words.group_by { |word| word.downcase.chars.sort }.values

	p anagrams
	
end
words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'hello', 'Hello']
combine_anagrams(words)

