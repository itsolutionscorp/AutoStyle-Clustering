#!/bin/ruby

def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|
    anag = w.downcase.chars.sort.join
    if !hash[anag] then
      hash[anag] = []
    end
    hash[anag] << w
  end
  hash.values
end

def a_test
  puts combine_anagrams(
    ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  )
end

