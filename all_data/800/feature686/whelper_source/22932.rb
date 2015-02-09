def combine_anagrams(words)
  anagrame = words.group_by { |word| word.downcase.chars.sort }
  puts("anagrames=")
  p(anagrame.values)
end

