#! /usr/bin/env ruby

def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    found = false
    anagrams.each do |anagram|
      if anagram[0].downcase.split("").sort.join == word.downcase.split("").sort.join
        found = true
        anagrams[anagrams.index(anagram)].push word
        break
      end
    end
    unless found
      anagrams.push [word]
    end
  end
  return anagrams
end

