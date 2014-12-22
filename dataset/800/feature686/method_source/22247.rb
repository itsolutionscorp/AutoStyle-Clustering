def combine_anagrams(words)
  words.group_by { |word| word.downcase.split("").sort }.values
end