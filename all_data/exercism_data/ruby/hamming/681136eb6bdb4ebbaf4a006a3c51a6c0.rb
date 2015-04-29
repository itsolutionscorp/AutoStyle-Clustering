module Hamming

  def self.compute(seq1, seq2)
    seq1 = seq1.split('') if seq1.class == String
    seq1.slice!(seq2.length, seq1.length) if seq1.length > seq2.length
    seq2 = seq2.split('') if seq2.class == String
    seq2.slice!(seq1.length, seq2.length) if seq2.length > seq1.length

    distance(seq1, seq2)
  end

  def self.distance(seq1, seq2)
    difference(seq1, seq2).reduce(:+)
  end

  def self.difference(seq1, seq2)
    less, more = [seq1, seq2].sort_by(&:length)
    less.zip(more).map { |i, j| (i <=> j).abs }.
      concat(Array.new((more.length - less.length), 1))
  end

end
