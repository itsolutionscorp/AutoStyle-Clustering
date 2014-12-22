def combine_anagrams(words)
  words.group_by{ |w| w.downcase.chars.sort.join}.values
end

puts combine_anagrams ['cars', 'for', 'poTaToes', 'racs', 'four','scar', 'creams', 'scream']