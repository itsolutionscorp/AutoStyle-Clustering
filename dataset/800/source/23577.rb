#!/usr/bin/env ruby

def combine_anagrams(words)
  hash = {}
  words.each do |word|
    anagram_id = word.downcase.chars.sort.join
    if not hash.has_key?(anagram_id)
      hash[anagram_id] = []
    end
    hash[anagram_id].push(word)
  end
  return hash.each_value.to_a
end

anagrams = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
result = combine_anagrams(anagrams)
puts %q{expected: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]}
puts "actual: #{result}" 
