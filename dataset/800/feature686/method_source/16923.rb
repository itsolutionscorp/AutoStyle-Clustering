def combine_anagrams(words)
  wordHash = Hash.new([])
  words.each { |word| wordHash[word.downcase.split(//).sort.join] += [word] }
  return wordHash.values
end