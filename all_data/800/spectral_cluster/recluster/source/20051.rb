#!/usr/local/bin/ruby
# input: 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  count = {}
  words.each do |w| 
     count[w.downcase.chars.sort.join] ||= []
     count[w.downcase.chars.sort.join] << w
  end
  ret = []
  count.each_key do |c| 
    ret << count[c] 
  end
  return ret
end
#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#print combine_anagrams(input)
