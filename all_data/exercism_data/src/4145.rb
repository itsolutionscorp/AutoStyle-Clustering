def compute(strand1, strand2)
    (0...[strand1.length, strand2.length].min).count{|index| strand1[index] != strand2[index]}
  end