#!/usr/bin/env ruby

# input: 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  h = Hash.new

  words.each do |word|
    if h[word.downcase.chars.sort.join] == nil
      h[word.downcase.chars.sort.join] = Array.new
    end
#    if h[word.downcase.chars.sort.join].include?(word) == false 
      h[word.downcase.chars.sort.join] << word 
#    end
  end

  new_words = Array.new

  h.each_value do |val|
    new_words << val
  end

  return new_words

end

if __FILE__ == $0

words = ['cars', 'Cars', 'Cars', 'CarS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

puts "#{combine_anagrams(words).inspect}"  

end
