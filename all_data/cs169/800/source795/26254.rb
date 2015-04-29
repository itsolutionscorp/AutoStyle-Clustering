input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']


def combine_anagrams(words)
  h = words.group_by {|word| word.chars.sort}
  print h.values
end

combine_anagrams(input)