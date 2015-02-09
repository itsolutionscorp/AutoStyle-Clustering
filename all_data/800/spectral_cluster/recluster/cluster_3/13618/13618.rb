#!/usr/bin/env ruby

def combine_anagrams(words)
  sorted = words.map {|w| w.downcase.split('').sort}
  known = []
  result = []
  n = words.length
  words.each_index do |i|
    if known.index(i) == nil
      # word is new, should be processed
      palindroms = [words[i]]
      known.push(i)
      for j in i+1..n
        if known.index(j) == nil and sorted[i] == sorted[j]
          palindroms.push(words[j])
          known.push(j)
        end
      end
      result.push(palindroms)
    end
  end
  return result
end

