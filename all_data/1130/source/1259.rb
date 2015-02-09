#! /usr/bin/env ruby

def combine_anagrams(words)
	groups = {}
	words.each do |word|
		letters = word.downcase.chars.sort.join
		groups[letters] ||= []
		groups[letters] << word
	end
	groups.values
end

# print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])