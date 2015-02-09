def combine_anagrams(words)
  words.map { |w| find_anagrams(w, words) }.uniq
end