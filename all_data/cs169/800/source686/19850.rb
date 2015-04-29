#!/usr/bin/env ruby

# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  aMap = {}
  words.each { |w|
    key = w.downcase.chars.sort
    aMap[key] = aMap[key] != nil ? aMap[key] + [w] : [w]
  }
  #words.each { |w| aMap[w.downcase.chars.sort] = aMap[w.downcase.chars.sort] = w}
  aMap.values.to_ary
end
