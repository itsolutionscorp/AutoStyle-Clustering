#!/usr/bin/env ruby

def combine_anagrams(words)
  result, t_result = [], []
  
  words.each do |word|
    t_word = word.downcase.split(//).sort.join('').delete(" ")
    if !t_result.include?(t_word)
      t_result << t_word
      result << ([] << word)
    else
      result[t_result.index(t_word)] << word
    end
  end
  
  result
end


# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

