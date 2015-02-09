def combine_anagrams(words)
  ana = words.group_by { |word| word.chars.sort }.values
  return ana
end

