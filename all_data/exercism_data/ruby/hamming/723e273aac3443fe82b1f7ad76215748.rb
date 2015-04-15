class Hamming
  def self.compute(seq1, seq2)
    seq1.chars.zip(seq2.chars).reduce(0) {|sum, cur| sum + ((cur[0] && cur[1] && cur[0] != cur[1]) ? 1 : 0)}
  end
end
