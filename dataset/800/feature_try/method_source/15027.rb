def combine_anagrams(words)
  raise(ArgumentError) unless words.kind_of?(Array)
  keys = words.collect { |w| anagram_key(w) }.uniq
  anagrams = keys.collect { |k| words.select { |w| (anagram_key(w) == k) } }
end