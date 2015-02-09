def combine_anagrams(words)
  anagrams = Hash.new { |h, k| h[k] = [] }
  words.each { |word| (anagrams[word.downcase.chars.sort.join] << word) }
  anagrams.values
end