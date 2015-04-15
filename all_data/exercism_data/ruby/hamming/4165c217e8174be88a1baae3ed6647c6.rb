class Hamming
  def self.compute(seq1, seq2)
    if seq1.length > seq2.length
      countHammingDistance(seq2, seq1)
    else
      countHammingDistance(seq1, seq2)
    end
  end

  private

  def self.countHammingDistance(smaller_seq, seq)
    smaller_seq.chars.each_with_index.inject(0) do |count, (value, index)|
      count + 1 if value != seq[index]
      count
    end
  end
end
