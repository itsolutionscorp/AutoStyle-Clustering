def combine_anagrams(words)
  words.map { |word1| words.select { |word2| anagrams?(word1, word2) } }.uniq
end