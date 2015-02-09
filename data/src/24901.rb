#!/usr/bin/env ruby
def combine_anagrams(words)
  word_hash = {}
  words.each do |w|
    sorted_word = w.downcase.chars.sort.join
    if !word_hash.has_key?(sorted_word)
      word_hash[sorted_word] = [w]
    else
      word_hash[sorted_word] << w
    end
  end
  return word_hash.values
end
#puts combine_anagrams(['carS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
#puts combine_anagrams([]).inspect
