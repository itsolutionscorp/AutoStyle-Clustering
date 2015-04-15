def combine_anagrams(words)
  h = Hash.new { |h, k| h[k] = [] }
  words.each { |word| (h[word.downcase.chars.sort.join] << word) }
  return h.values
end

