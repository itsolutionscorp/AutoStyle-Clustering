def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    (anagrams << words.select { |w| (w.sort == word.sort) })
  end
  anagrams.uniq!
end