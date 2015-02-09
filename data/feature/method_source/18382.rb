def combine_anagrams(words)
  word_hash = Hash.new { |hash, key| hash[key] = [] }
  words.each { |w| (word_hash[w.downcase.scan(/\w/).sort.join] << w) }
  word_hash.values
end