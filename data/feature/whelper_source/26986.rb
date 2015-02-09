def combine_anagrams(words)
  sorted = []
  words.each do |string|
    (sorted << string.chars.sort { |a, b| a.casecmp(b) }.join.downcase)
  end
  uniq_sorted = sorted.uniq
  anagrams = []
  uniq_sorted.each do |string1|
    angrm = []
    sorted.each_index do |index|
      (angrm << words[index]) if (string1 == sorted[index])
    end
    (anagrams << angrm)
  end
  anagrams
end

