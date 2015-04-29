def combine_anagrams(words)
  # words is an array
  anagrams = words.group_by { |word| word.chars.sort }.values
  return anagrams
end

p (combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']))
