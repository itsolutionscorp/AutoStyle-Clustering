def compute strand1, strand2
    (strand1.size.times).count{|i| strand1[i] != strand2[i] }
  end