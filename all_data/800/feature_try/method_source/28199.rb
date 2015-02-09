def combine_anagrams(words)
  ret = {}
  words.each do |word|
    ret[anagram_code(word)] ||= []
    (ret[anagram_code(word)] << word)
  end
  ret.values.inject([]) { |o, v| (o << v) }
end