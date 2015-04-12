class Hamming
  def compute (seq1, seq2)
    min_seq = [seq1.size, seq2.size].min
    min_seq.times.count { |i| seq1[i] != seq2[i] }
  end
end
