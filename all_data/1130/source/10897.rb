#!/usr/bin/env ruby

def combine_anagrams(words)

  output = Hash.new { |h,k| h[k] = [] }

  words.each { |w| output[w.downcase.chars.sort.join] << w }

  output.values

end
