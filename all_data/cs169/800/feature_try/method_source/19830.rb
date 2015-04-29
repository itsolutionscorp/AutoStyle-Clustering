def combine_anagrams(words)
  arr = []
  words.each { |word| (arr << anagram_arr?(word, words)) }
  return arr.uniq
end