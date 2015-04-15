#!/usr/bin/env ruby


def sort_word(string)
    chars = []
    string.downcase.each_char {|c| chars << c  }
    chars.sort.join
end

def combine_anagrams(words)
  result = [] 
  h = Hash.new { |hash, key| hash[key] = [] }
  words.each {|word| h[sort_word(word)] << word }
  h.each {|k,v| result << v}
  puts h.inspect
  result
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).inspect


