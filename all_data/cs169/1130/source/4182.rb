def combine_anagrams(words)
  words.each_with_object(Hash.new []) do |word, hash|
     hash[word.downcase.sum] += [word.downcase]
     hash[word.downcase.sum].inspect
  end
end
