#!/usr/bin/ruby

def combine_anagrams(words)
  anagrams = {}

  words.each do |word|
    normalized = word.downcase.chars.sort.join
    (anagrams[normalized] ||= []) << word
  end

  anagrams.values
end