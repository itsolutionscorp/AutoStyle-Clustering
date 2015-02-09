def combine_anagrams(words)
  answer = words.group_by { |word| word.chars.sort }.values
  return answer
end