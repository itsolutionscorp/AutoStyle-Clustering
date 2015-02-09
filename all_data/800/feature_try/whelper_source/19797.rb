def anagram(word1, word2)
  return true if (word1.downcase.chars.sort == word2.downcase.chars.sort)
  return false
end

def combine_anagrams(words)
  result = []
  while (words.length > 0) do
    word = words.shift
    word_vect = []
    word_vect[0] = word
    i = (words.length - 1)
    while (i >= 0) do
      if anagram(word, words[i]) then
        word_vect.insert(1, words[i])
        words.delete_at(i)
      end
      i = (i - 1)
    end
    result.insert(-1, word_vect)
  end
  puts(result.length)
  return result
end

