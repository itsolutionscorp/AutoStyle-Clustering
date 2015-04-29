def anagram_arr?(word, arr)
  out = []
  arr.each { |val| (out << val) if (anagram?(word, val) == 0) }
  return out
end

def combine_anagrams(words)
  arr = []
  words.each { |word| (arr << anagram_arr?(word, words)) }
  return arr.uniq
end

