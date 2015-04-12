def compute(seq1, seq2)
    seq2 = seq2.split('').reverse
    seq1.split('').reduce(0) {|dif, cur| dif + ((!seq2.empty? && cur != seq2.pop) ? 1 : 0)}
  end