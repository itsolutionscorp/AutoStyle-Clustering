class Hamming
  def compute(word1, word2)
  return 0 if word1 == word2
  raise ArgumentError, "Words must be the same size." if word1.size != word2.size

  count = 0
  word1.size.times do |i|
    count += 1 if word1[i] != word2[i]
  end

  count
  end
end
