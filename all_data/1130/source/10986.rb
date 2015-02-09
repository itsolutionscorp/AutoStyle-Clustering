def combine_anagrams(words)
  anagram_group = Hash.new([])
  words.each {|word| anagram_group[word.downcase.split(//).sort.join] += [word]}
  return anagram_group.values
end