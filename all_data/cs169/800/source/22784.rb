def combine_anagrams(words)
  words.group_by { |word| word.downcase.chars.sort }.values
end
