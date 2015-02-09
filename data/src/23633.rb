#!/usr/bin/ruby

def sortArray(array)

	array.sort! { |a,b| a.name.downcase <=> b.name.downcase }

end

def combine_anagrams(words)

	words.group_by { |word| word.chars.sort }.values

end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
