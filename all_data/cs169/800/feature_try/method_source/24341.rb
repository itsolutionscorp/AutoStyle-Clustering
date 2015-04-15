def combine_anagrams(words)
  anagrams = Array.new
  uniques = words.map { |w| wordsort(w) }.uniq
  uniques.each { |u| (anagrams << words.find_all { |w| (wordsort(w) == u) }) }
  return anagrams
end