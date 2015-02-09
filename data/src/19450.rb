def combine_anagrams(words)
  tmp_hash = Hash.new([])
  words.each { |word| tmp_hash[word.downcase.split(//).sort] += [word] }
  tmp_hash.values
end