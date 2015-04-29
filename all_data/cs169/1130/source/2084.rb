# Author: Brian Cox
# Date: 2012-04-29 00:08 EDT (-4:00)
# HW1: Ruby calishtenics
# Part 3: anagrams

def combine_anagrams(words)
  words.group_by {|w| w.downcase.chars.sort {|a,b| a <=> b}.to_s}.values
end
