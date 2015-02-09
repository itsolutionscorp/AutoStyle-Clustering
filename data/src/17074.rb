def combine_anagrams(words)
  combined = Hash.new { |hash, key| hash[key] = [] }
  words.each { |word| (combined[word.downcase.split(//).sort.join] << word) }
  combined.values
end