def combine_anagrams(words)
  anagram_map = {}
  words.each do |word|
    ((anagram_map[word.downcase.split(//).sort.join] ||= []) << word)
  end
  anagram_map.values
end

