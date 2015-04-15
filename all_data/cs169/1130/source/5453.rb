def combine_anagrams(words)
  anagrams = words.group_by { |word| Array(word.downcase.each_char).sort.join }
  anagrams.values
end
