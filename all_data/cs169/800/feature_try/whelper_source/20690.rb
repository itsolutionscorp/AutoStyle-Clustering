def find_anagrams(word, words)
  words.select { |w| are_anagrams(word, w) }
end

def combine_anagrams(words)
  words.map { |w| find_anagrams(w, words) }.uniq
end

