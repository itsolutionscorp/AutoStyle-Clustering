#!/usr/bin/env ruby
def combine_anagrams(words)
  hash = {}
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if !hash[sorted]
      hash[sorted] = []
    end
    hash[sorted] << word
  end 
  hash.values
end

if __FILE__ == $PROGRAM_NAME
  input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  output = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
  raise unless combine_anagrams(input) == output
end
