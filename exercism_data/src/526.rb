def compute(item_a, item_b)
    #create array of pairs equal to the size of the smaller string
    hamming_pairs = item_a.chars.zip(item_b.chars)
    #calculate hamming distance
    hamming_pairs.map { |pair| pair.first != pair.last ? 1 : 0 }.reduce(:+)
  end