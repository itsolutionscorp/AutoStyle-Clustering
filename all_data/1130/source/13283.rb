#!/usr/bin/env ruby

def anagrams?(w1,w2)
  return w1.downcase.chars.sort.join == w2.downcase.chars.sort.join
end

def combine_anagrams(words)
  if words.length == 0 ; return [] end
  result = Array.new
  part = words
  while part.length > 0 do
    tmp = part.partition { |w| anagrams?(w,part[0]) }
    result = result + [ tmp[0] ]
    part = tmp[1]
  end
  return result
end
