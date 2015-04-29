#!/usr/bin/ruby

def combine_anagrams(words)
  output = Array.new
  while w = words.slice!(0)
    anagrams = Array.new
    anagrams << w
    sorted = anagrams[0].downcase.each_byte.sort
    words.collect! do |word|
      if sorted == word.downcase.each_byte.sort
        anagrams << word
        nil
      else
        word
      end
    end
    words.compact!
    output << anagrams
  end
  output
end


=begin Test
input = 
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
output =  
[["cars", "racs", "scar"], ["for"], ["potatoes"], ["four"], ["creams", "scream"]]

p combine_anagrams(input)
p output
=end

