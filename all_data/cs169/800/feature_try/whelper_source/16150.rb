def combine_anagrams(words)
  anagrams = words.group_by { |word| word.downcase.chars.sort }.values
  anagrams
end

