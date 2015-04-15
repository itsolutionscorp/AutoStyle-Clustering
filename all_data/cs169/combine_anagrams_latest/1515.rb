#!/usr/bin/env ruby
# encoding: utf-8
#
# Part3 of HW 1
# Created by Daniel Schnell
#

def combine_anagrams(words)
  res = Hash.new([])
  words.each do |word|
    ano = word.downcase.chars.sort.join
    res[ano] += [word]
  end
  res.values.sort
end

=begin
res = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts res.inspect
res = combine_anagrams([])
puts res.inspect
res = combine_anagrams(['cars', 'CARS', 'sCaR', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts res.inspect

=end
