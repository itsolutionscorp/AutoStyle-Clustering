#! /usr/bin/env ruby

def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    if !(h.has_key?(word.downcase.each_char.sort.join))
      h[word.downcase.each_char.sort.join] = []
    end
    h[word.downcase.each_char.sort.join].push(word)
  end
  return h.values
end
