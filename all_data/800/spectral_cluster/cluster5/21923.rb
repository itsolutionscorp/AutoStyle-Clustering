#!/usr/bin/env ruby

# 3
def combine_anagrams(words)
  anagrams_list = words.map{ |word| word.chars.sort.join }.uniq

  anagrams = []
  anagrams_list.each do |anagram|
  	anagrams << words.select{ |word| word.chars.sort.join == anagram }
  end

  anagrams
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
