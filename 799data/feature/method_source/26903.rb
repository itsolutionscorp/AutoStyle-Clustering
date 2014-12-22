def combine_anagrams(words)
  h = Hash.new { |hash, key| hash[key] = Array.new }
  words.each { |word| (h[word.downcase.split(//).sort.join] << word) }
  h.values
end