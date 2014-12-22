def combine_anagrams(words)
  words2 = words.group_by { |word| word.chars.sort }.values
  return words2
end

