def compute(seq1, seq2)
    diffs = seq1.chars.select.with_index { |c, i| c != seq2[i] }
    diffs.length
  end