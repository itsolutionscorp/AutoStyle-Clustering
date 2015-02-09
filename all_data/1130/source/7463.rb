#!/usr/bin/env ruby
def combine_anagrams(words)

 words.group_by { |word| word.downcase.chars.sort }.values
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])