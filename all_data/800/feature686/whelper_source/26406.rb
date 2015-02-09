def combine_anagrams(words)
  words.map do |word1|
    words.select { |word2| (word1.downcase.sort == word2.downcase.sort) }
  end.reject do |wordset|
    (wordset == [])
  end.uniq
end

