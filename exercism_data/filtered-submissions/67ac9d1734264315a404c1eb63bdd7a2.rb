def compute(strand1, strand2)
    ham = 0
    for i in 0...strand1.length
      if strand1[i] != strand2[i]
        ham += 1
      end
    end
    return ham
  end