def combine_anagrams(words)
  words.group_by { |word| word.chars.sort }.values
end

