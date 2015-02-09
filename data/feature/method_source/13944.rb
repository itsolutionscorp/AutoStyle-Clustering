def combine_anagrams(words)
  h = {}
  words.each do |word|
    word2 = word.downcase.chars.sort
    h.has_key?(word2) ? (h[word2] = (h[word2] + [word])) : (h[word2] = [word])
  end
  h.values
end