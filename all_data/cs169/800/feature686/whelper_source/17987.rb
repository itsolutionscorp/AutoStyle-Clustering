def combine_anagrams(words)
  anagrams = words.map { |x| x.downcase.split(//).sort.join }
  uniq_anagrams = anagrams.uniq
  combined_anagrams = []
  for word in uniq_anagrams do
    (combined_anagrams << words.select { |w| (w.downcase.split(//).sort.join == word) })
  end
  combined_anagrams
end

