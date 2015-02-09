#!/usr/bin/ruby

def combine_anagrams(words)
  anagram = Hash.new
  words.each do |w|
    key = w.to_s.chars.sort.join
    anagram[key] ||= []
    anagram[key] << w.to_s
  end
  anagram.values.sort
  #anagram.values.inspect
end

#abc = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#abc = ['c','a','b','a']
#abc = []
#abc = ["a", "A"]
#abc = ["HeLLo", "hello"]
#puts combine_anagrams abc 

