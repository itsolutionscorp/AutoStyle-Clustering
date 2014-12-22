def combine_anagrams(words)
  h = {}
  words.each {|word| h[word.downcase.chars.sort] = (h[word.downcase.chars.sort] || []) << word}
  h.values
end