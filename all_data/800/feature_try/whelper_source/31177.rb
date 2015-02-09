def combine_anagrams(words)
  anagram = words.group_by { |word| word.chars.sort }.values
end

