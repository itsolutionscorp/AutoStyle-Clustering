def combine_anagrams(words)
  clone = Array.new(words)
  words.reduce([]) do |memo, word|
    aux = word.downcase.split(//).sort.join
    matches, clone = clone.partition do |test_word|
      (test_word.downcase.split(//).sort.join == aux)
    end
    (memo << matches) unless (matches.size == 0)
    memo
  end
end