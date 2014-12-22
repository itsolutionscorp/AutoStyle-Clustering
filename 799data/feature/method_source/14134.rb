def combine_anagrams(words)
  words.group_by { |word| word.chars.sort.join.downcase }.values
end