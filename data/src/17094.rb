def combine_anagrams(words)
  lower_case = words.collect { |word| word.downcase }
  anagrams = lower_case.group_by { |lower_case2| lower_case2.chars.sort }.values
end