#!/usr/local/bin/ruby


def combine_anagrams(words)

  h = {}
  words.each do |word|
    key = word.downcase.chars.sort
    if (!h.has_key?(key))
      h[key] = Array.new()
    end
      h[key].push(word)
  end
  a = Array.new()
#puts "the hash: #{h}"
  h.each do |key,value|
#puts "the value: #{value}"
    a[a.length] = value
#puts "a is #{a}"
  end
  return a
end

#puts combine_anagrams(['car','rac','cat','TAC'])
