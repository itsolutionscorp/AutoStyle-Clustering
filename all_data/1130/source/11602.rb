#!/usr/bin/env ruby


def combine_anagrams(words)
  word_group = {}
  words.each { |word|
    sorted_word = word.downcase.split(//).sort.join
    if word_group[sorted_word]
      word_group[sorted_word].push(word)
    else
      word_group[sorted_word] = [word]
    end 
  }

  collate_arrays = []
  word_group.each_value { | value |
    collate_arrays.push(value)
  }

  return collate_arrays
end

combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )

