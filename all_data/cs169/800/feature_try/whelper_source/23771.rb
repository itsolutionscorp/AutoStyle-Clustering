def combine_anagrams(words)
  result = {}
  for word in words do
    (result[sorted_word(word)] = [word] unless result[sorted_word(word)]
    for word2 in words do
      (next if (word == word2)
      if result[sorted_word(word2)] and (not result[sorted_word(word2)].include?(word2)) then
        (result[sorted_word(word2)] << word2)
      end)
    end)
  end
  [result.values]
end

def sorted_word(w)
  w.downcase.split("").sort.join
end

