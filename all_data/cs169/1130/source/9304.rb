#!/usr/bin/env ruby

def anagram?(word1, word2)
  sorted_word1 = word1.downcase.chars.sort.join
  sorted_word2 = word2.downcase.chars.sort.join

  if sorted_word1 == sorted_word2
    return word2
  else
    return nil
  end
end

def combine_anagrams(words)
  anagrams = {}
  words.uniq.each do |word|
    anagrams[word] = words.map { |word2| anagram?(word, word2) }.compact!
  end
  return anagrams.values.uniq
end
