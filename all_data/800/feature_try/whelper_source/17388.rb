def combine_anagrams(words)
  anagramHash = words.group_by { |word| anagramKey(word) }
  anagramHash.values
end

def anagramKey(word)
  word.downcase.chars.sort.join
end

