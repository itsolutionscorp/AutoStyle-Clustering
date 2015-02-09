def combine_anagrams(words)
  r = Hash.new([])
  words.each { |word| r[word.downcase.split(//).sort.join] += [word] }
  return r.values
end