def compute(sq1, sq2)
    sq1 = sq1[0...sq2.size] if sq1.length > sq2.length
    (0...sq1.size).select { |i| sq1[i] != sq2[i] }.count
  end