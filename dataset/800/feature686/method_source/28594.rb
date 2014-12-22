def combine_anagrams(words)
  hash = Hash.new([])
  words.each { |w| hash[w.downcase.chars.sort.join] += [w] }
  return hash.values
end