def combine_anagrams(anagrams)
  anagram_hash = anagrams.inject({}) do |hash, element|
    sorted = element.downcase.split(//).sort.join
    hash[sorted] = hash[sorted] ? ((hash[sorted] << element)) : ([element])
    hash
  end
  anagram_hash.values
end