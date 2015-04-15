def compute(strand_1, strand_2)
    return 0 if strand_1.length != strand_2.length
    count = 0
    for i in 0..strand_1.length
      count += 1 if strand_1[i] != strand_2[i]
    end
    count
  end