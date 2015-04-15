#!/usr/bin/env ruby

# input:
#words = ['caRs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  result = Hash.new
  words.each do |word|
    sw = word.downcase.split(//).sort
    if !result.has_key?(sw)
      result[sw] = Array.new
    end
    result[sw] << word
  end
  result.values
end

#puts combine_anagrams(words).to_s
