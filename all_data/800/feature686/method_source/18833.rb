def combine_anagrams(words)
  anagrams = words.group_by { |word| word.downcase.chars.sort }
  anagrams = anagrams.values.map { |word_group| word_group.sort }.sort
  anagrams
end