def compute(strand1, strand2)
    hamming = 0
    for x in 0...[strand1,strand2].min.length
      hamming += 1 if strand1[x] != strand2[x]
    end
    hamming
  end