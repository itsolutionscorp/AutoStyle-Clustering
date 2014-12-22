def combine_anagrams(words)
  hash = Hash.new([])
  words.map{|word| hash[word.downcase.chars.sort.join] += [word] }
  hash.values
end