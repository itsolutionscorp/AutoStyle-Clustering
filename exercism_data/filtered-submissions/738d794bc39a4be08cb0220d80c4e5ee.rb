def compute(strand_1, strand_2)
    pairs = strand_1.split('').zip(strand_2.split(''))
    pairs.count { |pair| pair[0] && pair[1] && pair[0] != pair[1]}
  end