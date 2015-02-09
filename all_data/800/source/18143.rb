#!/usr/bin/env ruby

def combine_anagrams(words)
  anagramGroups = Hash.new
  words.collect do |word|
    wordAnagram = word.downcase.chars.sort
    if anagramGroups.has_key? wordAnagram
      anagramGroups[wordAnagram] << word
    else
      anagramGroups[wordAnagram] = [word]
    end
  end
  anagramGroups.values
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]