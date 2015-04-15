#!/usr/bin/env ruby

def combine_anagrams(words)
  anagr_pos = Hash.new

  result = []

  words.each { |s|
     anagr = s.downcase.chars.sort.join
     if (anagr_pos[anagr] == nil)
       anagr_pos[anagr] = result.length
       result << [s]
     else
       result[anagr_pos[anagr]] << s
     end
  }
  result
end

