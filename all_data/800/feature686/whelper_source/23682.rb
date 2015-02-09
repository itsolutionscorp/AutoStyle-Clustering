def combine_anagrams(words)
  hash = Hash.new([])
  words.each { |str| hash[str.downcase.split(//).sort.join] += [str] }
  hash.values
  hash.values.to_s
end

