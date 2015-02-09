#!/usr/local/bin/ruby

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
class String
  def sort
    self.split(//).sort.join
  end
end

def combine_anagrams(words)
  anagram = {}

  words.each{ |word|
    if (anagram[word.sort])
      anagram[word.sort].push(word)
    else
      anagram[word.sort] = [word]
    end
  }

  map = []
  anagram.each_pair{|key,value| map.push(value)}
  map
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s

