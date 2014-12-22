def combine_anagrams(words)
  array = []
  create_anagram_hash(words).each_pair { |key, value| (array << value) }
  array
end