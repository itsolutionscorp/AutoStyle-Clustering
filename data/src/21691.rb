def combine_anagrams(words)
  words_hash = {}
  for word in words do
    (if (words_hash[word.downcase.chars.sort.join] == nil) then
      words_hash[word.downcase.chars.sort.join] = []
    end
    (words_hash[word.downcase.chars.sort.join] << word))
  end
  return words_hash.values
end