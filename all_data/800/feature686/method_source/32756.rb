def combine_anagrams(words)
  result = words.group_by { |word| word.downcase.chars.sort }.values
  return result
end