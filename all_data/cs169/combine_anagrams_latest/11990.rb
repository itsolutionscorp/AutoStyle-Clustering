#-------------------------------------------------
#
# Script Name:  hw1_p3
# Version:      1.0
# Author:       Kasif Gilbert
# Date:         May 26, 2012
#
# Description: Homework 1 Part 3
#
#-------------------------------------------------

def combine_anagrams(words)
  res = {}
  
  words.each do |word|
    key = word.downcase.split('').sort.join
    res[key] ||= []
    res[key] << word
  end
  
  p res.values
  
end

combine_anagrams(['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'crEams', 'scream'])
