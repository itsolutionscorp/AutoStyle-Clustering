def combine_anagrams(words)
  return words.group_by { |element| element.downcase.chars.sort }.values
end
