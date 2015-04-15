#!/usr/bin/ruby

# Anagrams

def combine_anagrams(words)
  h = Hash.new()
  words.each do |word|
    h.has_key?(word.downcase.chars.sort.join) ? h[word.downcase.chars.sort.join] << word : h[word.downcase.chars.sort.join] = [].push(word)
  end
  a = Array.new
  h.each do |index, group| 
    a.push(group) 
  end
  return a
end

#input = ['cars','for','potatoes','racs','four','scar','creams','scream']
#input = []
input = ['four','for']

puts combine_anagrams(input)
