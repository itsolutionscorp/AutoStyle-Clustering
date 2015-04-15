#!/usr/bin/ruby

def combine_anagrams(words)
  (words.group_by {|w| w.downcase.split("").sort.join}).values
end
 
#puts combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#puts  '[["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]'
 




