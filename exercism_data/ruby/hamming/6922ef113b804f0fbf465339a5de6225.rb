module Hamming

  def self.compute(seq1, seq2)
    seq1 = seq1.split('') if seq1.class == String
    seq2 = seq2.split('') if seq2.class == String
    seq1.slice!(seq2.length, seq1.length) if seq1.length > seq2.length

    distance(seq1, seq2)
  end

  def self.distance(seq1, seq2)
    difference(seq1, seq2).reduce(:+)
  end

  def self.difference(seq1, seq2)
    seq1.zip(seq2).map { |i, j| (i <=> j).abs }
  end

end
