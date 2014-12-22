def combine_anagrams(words)

  hash = words.each_with_object(Hash.new []) do |word, hash|
    hash[word.downcase.split('').sort.join] += [word]
  end
  hash.values
end