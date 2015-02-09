#!/usr/local/bin/ruby -w

# input: 
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
# #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
# ["creams", "scream"]]
# # HINT: you can quickly tell if two words are anagrams by sorting their
# #  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  sortedWords = words.collect{|x| [x, x.chars.sort{|a,b| a.casecmp(b)}.join.downcase]}
  hashMap = Hash.new()
  sortedWords.each{|x| hashMap.has_key?(x[1])? hashMap[x[1]] += [x[0]] : hashMap[x[1]] = [x[0]] } 
  return hashMap.values

end
puts combine_anagrams( ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts combine_anagrams([])
puts combine_anagrams(['a', 'b', 'c', 's'])
puts combine_anagrams(['A', 'b', 'c', 's'])
