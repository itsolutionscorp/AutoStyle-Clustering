#!/usr/bin/ruby

def combine_anagrams(wwords)
  hash = {}
  wwords.each do |word|
    key = word.downcase.split('').sort.join
    hash[key] = [] if hash[key].nil?
    hash[key].push(word)
  end
  hash.values.to_a
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
