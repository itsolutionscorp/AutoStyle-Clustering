def combine_anagrams(words)
  words.map { |word| words.select { |w| w.downcase.chars.sort == word.downcase.chars.sort } }.uniq
end

combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
