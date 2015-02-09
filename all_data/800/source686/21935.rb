#!/usr/bin/env ruby

require 'pp'

# \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_
# Anagrams
# \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  wordHash = Hash.new

  words.each {
    |word| 

    hashkey = word.downcase.chars.sort.join

    if (wordHash.include?(hashkey))
      wordHash[hashkey].push(word)
    else
      wordHash[hashkey] = Array[word]
    end

  }

  result = Array.new

  wordHash.each_value { |value| result.push(value) }

  return result

end

# \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_
# Test the code:
# \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

puts "Input:"
PP.pp(input, $>, 80)

puts "Output:"
PP.pp(combine_anagrams(input), $>, 80)

