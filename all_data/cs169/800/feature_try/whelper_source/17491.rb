def combine_anagrams(words)
  anagrams = []
  while (not words.empty?) do
    w = words.pop
    anagram_el = []
    (anagram_el << w)
    words.each { |word| (anagram_el << word) if is_anagram(w, word) }
    anagram_el.each { |word| words.delete(word) }
    (anagrams << anagram_el)
  end
  anagrams
end

def is_anagram(word1, word2)
  w1 = word1.downcase
  w2 = word2.downcase
  return (w1.chars.sort.join == w2.chars.sort.join) ? (true) : (false)
end

