# -*- mode: ruby -*-
# vi: set ft=ruby :

def anagram?(x,y)
  x.downcase.split('').sort == y.downcase.split('').sort
end

def combine_anagrams(words)
  anagrams = []
  flat = words.flatten
  while flat.length > 0
    if ana = flat.select{|word| anagram?(word, flat[0])}
      anagrams.push ana
    else
      anagrams.push [flat[0]]
    end
    flat = flat - anagrams[-1]
  end
  anagrams
end
