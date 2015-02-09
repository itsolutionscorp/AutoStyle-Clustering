def combine_anagrams(words)
  words.group_by { |word| [word.chars.to_a.sort] }.values
end