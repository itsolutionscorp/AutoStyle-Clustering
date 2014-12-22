#!/usr/local/bin/ruby

def combine_anagrams(words)
  hash = {}
  words.each do |word| 
    sword = word.downcase.chars.sort.join 
    hash[sword]||=[]
    hash[sword] << word
  end
  return hash.values
end

#puts combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts combine_anagrams([])
