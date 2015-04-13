def compute(seq1, seq2)
    length = [seq1.length, seq2.length].min
    pairs = seq1.chars.zip(seq2.chars).take(length)
    pairs.count { |first, second| first!= second }
  end