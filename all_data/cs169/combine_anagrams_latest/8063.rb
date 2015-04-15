#!/usr/bin/env ruby

def combine_anagrams(words)
    hash = Hash.new('')
    word_hash = words.each_with_object(Hash.new []) do |word, hash| 
      hash[word.downcase.chars.sort] += [word]
    end
    word_hash.values

end

#input = ['cArs','for','potatOes','raCs','four','sCar','crEams','screaM']
#p combine_anagrams(input)

