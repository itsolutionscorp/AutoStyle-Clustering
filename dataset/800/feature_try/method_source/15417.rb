def combine_anagrams(words)
  return words.group_by { |w| w.downcase.sort }.values
end