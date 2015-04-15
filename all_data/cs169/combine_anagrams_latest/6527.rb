def combine_anagrams(words)
  hash = Hash.new([])
  words.each{|word| hash[word.downcase.chars.sort] += [word]}
  hash.values
end