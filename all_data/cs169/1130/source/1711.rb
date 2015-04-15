#!/usr/bin/env ruby
#
# HW 1: Ruby calisthenics
#
# Part 3: anagrams
#

def combine_anagrams(words)
  words.group_by { |word| word.downcase.chars.sort }.values
end
