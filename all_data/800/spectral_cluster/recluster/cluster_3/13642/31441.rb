#!/usr/bin/env ruby

def combine_anagrams(words)
  ret = []
  words.each do
    |word|
    flag = 0
    ret.each do
      |x|
      xx = x[0].chars.sort { |a, b| a.casecmp(b) } .join
      wordx = word.chars.sort { |a, b| a.casecmp(b) } .join
      if xx.casecmp(wordx) == 0
        x << word
        flag = 1
        break
      end
    end
    if flag == 0
      ret << [word]
    end
  end
  return ret
end

my_arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts combine_anagrams(my_arr)
