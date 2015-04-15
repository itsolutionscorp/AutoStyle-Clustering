def combine_anagrams(words)
  words.group_by {|word| word.downcase.chars.sort}.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
