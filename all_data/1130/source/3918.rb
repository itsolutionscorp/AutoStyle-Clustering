
def combine_anagrams(words)
  hash =
  words.each_with_object(Hash.new []) do |word, hash|
    hash[word.downcase.sum] += [word]
  end
  hash.values
end