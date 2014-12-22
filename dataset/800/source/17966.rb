#!/usr/bin/env ruby
def combine_anagrams(words)
  sorted = words.map{|word| word.downcase.chars.sort { |a, b| a.casecmp(b) } .join}
  #calculating indexes of the anagrams
  indexed = Hash.new(Array.new)
  sorted.each_with_index{|elem, i| indexed[elem]+=[i] }
  result = []
  indexed.each_value{|val|
  result.push(val.map{|index| words[index]})
  }
  result
end

p combine_anagrams(['cArs', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream'])