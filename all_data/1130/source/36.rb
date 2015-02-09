#!/usr/local/bin/ruby

def combine_anagrams(words)
  # First make a hash array with the index being the sorted version of the word

  anagrams = Hash.new("")

  words.each { |word| anagrams[word.to_s.upcase.chars.sort.join] += word + " "} 

  # Now make a regular array with the words
  
  ana_reg = Array.new(0)  
  indx = 0
  anagrams.each {|word| ana_reg[indx] = word[1].scan(/\w+/)
                        indx = indx + 1}
  return ana_reg
end

# words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
words = ['Honey', 'honey']
puts "I am #{combine_anagrams(words)}"
 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], 
# ["potatoes"], ["creams", "scream"]]
