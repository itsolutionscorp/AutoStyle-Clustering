def combine_anagrams(words)
  groups = []
  while (words.count > 0) do
    word = words[0]
    group = words.select do |other_word|
      (word.downcase.split(//).sort == other_word.downcase.split(//).sort)
    end
    (groups << group)
    words = (words - group)
  end
  groups
end

