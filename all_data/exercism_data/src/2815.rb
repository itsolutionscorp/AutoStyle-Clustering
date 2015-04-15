def compute(seq1, seq2)
    return 0 if seq1.length != seq2.length
    sum = 0
    seq1.split('').each_index {|index| sum += (seq1[index] != seq2[index] ? 1 : 0)}
    sum
  end