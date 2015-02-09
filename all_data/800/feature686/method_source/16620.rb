def combine_anagrams(words)
  hash = {}
  words.each do |word|
    hash[word.downcase.chars.sort.join] ||= []
    (hash[word.downcase.chars.sort.join] << word)
  end
  return hash.values.to_a
end