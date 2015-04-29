class Hamming

  def self.compute(sequence1, sequence2)
    sequence1, sequence2 = [sequence1, sequence2].sort_by(&:length)
    pairs = sequence1.chars.zip(sequence2.chars)
    pairs.count { |from1, from2| from1 != from2 }
  end

end
