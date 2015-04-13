def compute(seq1, seq2)

    length = [seq1, seq2].map(&:to_s).map(&:length).min - 1
    (0..length).count { |i| seq1.to_s[i] != seq2.to_s[i] }
  end