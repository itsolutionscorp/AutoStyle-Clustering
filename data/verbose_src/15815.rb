#!/usr/bin/env ruby

def combine_anagrams(words)
  word_root=Hash.new
  words.each do |word|
    root=word.downcase.chars.sort.join
    if word_root.has_key?(root)
      word_root[root].push(word)
    else
      word_root[root]=Array.new
      word_root[root].push(word)
    end  
  end 
  word_root.values   
end

