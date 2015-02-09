def combine_anagrams(words = [])
  uniq_alphabetized_words = alphabetize(words)
  grouped_anagrams = []
  uniq_alphabetized_words.each do |uaw|
    group = []
    words.each do |w|
      (group << w) if (w.downcase.split("").sort.join == uaw)
      (grouped_anagrams << group)
    end
  end
  return grouped_anagrams.uniq
end