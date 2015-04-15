def combine_anagrams(words)
  anagrams = words.group_by { |word| word.chars.sort }.values
  p(anagrams)
end