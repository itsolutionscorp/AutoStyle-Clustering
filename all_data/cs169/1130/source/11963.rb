#!/usr/bin/ruby

def combine_anagrams(words)
  sorted_hash = Hash.new
  words.each do |word|
    sorted = word.downcase.chars.sort.join;
    if sorted_hash.include?(sorted)
      sorted_hash[sorted] << word;
    else
      sorted_hash[sorted] = [word];
    end
  end
  sorted_hash.values;
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
