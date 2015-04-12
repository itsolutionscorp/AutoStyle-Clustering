class Hamming
  def compute(seq1, seq2)
    seq1.chars.zip(seq2.chars).reduce(0) do |distance, pair|
      if pair[0] != pair[1]
        distance += 1
      end
      distance
    end
  end
end
