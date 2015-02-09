def combine_anagrams(words)
  anagrams = []
  until words.empty? do
    (group = words.select do |word|
      (word.downcase.scan(/./).sort == words.first.downcase.scan(/./).sort)
    end
    words = (words - group)
    (anagrams << group))
  end
  anagrams
end

