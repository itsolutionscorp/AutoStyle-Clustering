#!/usr/bin/env ruby

def combine_anagrams(words)
  dictionary = {}
  words.each do |w|
    normal = w.downcase.split(//).sort.join
    if not dictionary.has_key? normal
      dictionary[normal] = []
    end
    dictionary[normal].push(w)
  end
  return dictionary.values
end

