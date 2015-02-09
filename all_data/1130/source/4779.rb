#!/usr/bin/env ruby

def combine_anagrams(words)
    words.group_by { |word| word.downcase.split(//).sort.to_s }.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
