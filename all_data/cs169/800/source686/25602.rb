def combine_anagrams(words)
  words = words.group_by { |word| word.downcase.chars.sort }.values
end

w = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

w = combine_anagrams w
